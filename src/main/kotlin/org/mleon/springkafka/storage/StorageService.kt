package org.mleon.springkafka.storage

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import co.elastic.clients.util.ContentType
import org.apache.coyote.BadRequestException
import org.apache.http.HttpHeaders
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.message.BasicHeader
import org.apache.http.protocol.HttpContext
import org.elasticsearch.client.RestClient
import org.mleon.springkafka.dto.Product
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StorageService {
    private final val logger = LoggerFactory.getLogger(StorageService::class.java)
    private final val restClient: RestClient =
        RestClient.builder(
            HttpHost.create("http://localhost:9200"),
        )
            .setDefaultHeaders(arrayOf(BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)))
            .setHttpClientConfigCallback {
                HttpAsyncClientBuilder.create().addInterceptorLast { response: HttpResponse, _: HttpContext? ->
                    response.addHeader(
                        "X-Elastic-Product",
                        "Elasticsearch",
                    )
                }
            }
            .build()
    private final val transport = RestClientTransport(restClient, JacksonJsonpMapper())
    val esClient: ElasticsearchClient = ElasticsearchClient(transport)

    fun getByFilters(
        topic: String,
        value: String,
        field: String,
    ): List<Product?> {
        if (this.esClient.indices().exists { it.index(listOf(topic)) }.value().not()) {
            throw BadRequestException("Index $topic doesn't exist")
        }
        logger.info("Query to perform: Topic: $topic, field: $field, value: $value")
        val requestSearch =
            SearchRequest.of { index ->
                index.index(listOf(topic)).query { q ->
                    q.match {
                        it.field(field)
                        it.query(value)
                    }
                }
            }
        val result = this.esClient.search(requestSearch, Product::class.java)
        val hitResults = result.hits()
        this.logger.info("Found ${hitResults.total()?.value() ?: 0} product/s")
        return hitResults.hits().map {
            this.logger.info(it.explanation()?.toString() ?: "N/A")
            it.source()
        }
    }
}
