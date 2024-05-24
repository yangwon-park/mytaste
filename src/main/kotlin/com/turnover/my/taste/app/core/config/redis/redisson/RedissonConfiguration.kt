package com.turnover.my.taste.app.core.config.redis.redisson

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
class RedissonConfiguration {
    val mode: RedisMode? = RedisMode.SINGLE
    val password: String? = null
    val nodes: List<String>? = mutableListOf()

    enum class RedisMode {
        SINGLE, CLUSTER
    }
}