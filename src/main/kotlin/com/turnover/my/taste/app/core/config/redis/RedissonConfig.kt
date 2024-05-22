package com.turnover.my.taste.app.core.config.redis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
class RedissonConfiguration {
    private val mode: RedisMode? = null
    private val password: String? = null
    private val nodes: List<String>? = null

    internal enum class RedisMode {
        SINGLE, CLUSTER
    }
}