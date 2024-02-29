package org.mleon.springkafka

import org.apache.coyote.BadRequestException
import org.mleon.springkafka.storage.StorageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GetInfoByFilters(private val storageService: StorageService) {
    @GetMapping("search/{topic}")
    fun getByFilters(
        @RequestParam field: String,
        @RequestParam value: String,
        @PathVariable topic: String,
    ): Any {
        return runCatching { this.storageService.getByFilters(topic = topic, value = value, field = field) }
            .onFailure {
                val statusCode = if (it is BadRequestException) 404 else 500
                return ResponseEntity.status(statusCode).body(it.message ?: "Internal Server Error")
            }
            .onSuccess {
                return ResponseEntity.ok(it)
            }
    }
}
