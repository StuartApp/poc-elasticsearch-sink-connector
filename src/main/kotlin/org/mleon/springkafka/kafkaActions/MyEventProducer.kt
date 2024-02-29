package org.mleon.springkafka.kafkaActions

import org.mleon.springkafka.dto.Product
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class MyEventProducer(private val kafkaTemplate: KafkaTemplate<String, Product>) {
    private val logger = LoggerFactory.getLogger(MyEventProducer::class.java)

    fun sendMessage(
        message: Product,
        topicName: String,
    ) {
        val future: CompletableFuture<SendResult<String, Product>> = kafkaTemplate.send(topicName, message)
        future.whenComplete { result, error ->
            if (error == null) {
                this.logger.info("Message $message sent to topic: ${result.recordMetadata.topic()}")
            } else {
                this.logger.error("Unable to send $message due $error")
            }
        }
    }
}
