package org.mleon.springkafka.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    @JsonProperty("key")val key: Int,
    @JsonProperty("name")val name: String,
    @JsonProperty("value")val price: Int,
)
