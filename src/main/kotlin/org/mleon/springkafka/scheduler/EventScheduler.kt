package org.mleon.springkafka.scheduler

import org.mleon.springkafka.dto.Product
import org.mleon.springkafka.kafkaActions.MyEventProducer
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class EventScheduler(private val myEventProducer: MyEventProducer) {
    private val logger = LoggerFactory.getLogger(EventScheduler::class.java)

    @Scheduled(fixedDelay = 5000)
    private fun execute() {
        val endNumber = Random.nextInt(7, 50)
        this.logger.info("Starting to send $endNumber events")
        for (current in 1..endNumber) {
            Thread.sleep(30)
            val product = Product(key = current, name = "$current-${LocalDateTime.now()}", price = Random.nextInt(0, 100))
            this.myEventProducer.sendMessage(product, "poc-es")
        }
    }
}
