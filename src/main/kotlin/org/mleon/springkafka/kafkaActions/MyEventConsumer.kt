package org.mleon.springkafka.kafkaActions

import org.mleon.springkafka.dto.Product
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
@KafkaListener(topics = ["poc-es"], groupId = "foo")
class MyEventConsumer {
    private val logger = LoggerFactory.getLogger(MyEventProducer::class.java)

    @KafkaHandler
    fun listen(
            @Payload product: Product,
            @Header(KafkaHeaders.RECEIVED_PARTITION) partition: String,
    ) {
        this.logger.info("listened message. Key:${product.key}, Name: ${product.name}, Price: ${product.price}")
    }
}
