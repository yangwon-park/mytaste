package com.turnover.my.taste.app.core.config.redis

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer

private val log = KotlinLogging.logger {  }

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun startRedis() {
        redisServer = RedisServer.builder()
            .port(port)
            .setting(MAX_MEMORY_128M)
            .build()

        try {
            redisServer.start()
        } catch (e: RuntimeException) {
            if (e.message?.contains("bind:") == true) {
                log.warn { "Redis Already Run. Ignore Starting Process" }
            } else {
                log.error { e.message }
            }
        }
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }

    companion object {
        private const val MAX_MEMORY_128M = "maxmemory 128M"
    }
}