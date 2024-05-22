package com.turnover.my.taste.app.core.config.redis

import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RedissonClient
import org.redisson.spring.data.connection.RedissonConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

private val log = KotlinLogging.logger {  }

@Configuration
@EnableRedisRepositories
class RedisRepositoryConfig(

    @Value("\${spring.data.redis.host}")
    private val host: String,

    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    @Bean
    fun redisConnectionFactory(redissonClient: RedissonClient): RedisConnectionFactory {
        log.warn { "redissonClient :: $redissonClient" }
        log.warn { "redissonClient :: ${redissonClient.config.toYAML()}" }

        return RedissonConnectionFactory(redissonClient)
    }
}