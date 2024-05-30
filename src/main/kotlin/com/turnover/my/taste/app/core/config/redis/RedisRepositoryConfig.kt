package com.turnover.my.taste.app.core.config.redis

import com.turnover.my.taste.app.core.config.redis.redisson.RedissonClientBuilder
import com.turnover.my.taste.app.core.config.redis.redisson.RedissonConfiguration
import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RedissonClient
import org.redisson.spring.data.connection.RedissonConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisKeyValueAdapter
import org.springframework.data.redis.core.RedisKeyValueTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.mapping.RedisMappingContext
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

private val log = KotlinLogging.logger {  }

@Configuration
@EnableRedisRepositories(basePackages = ["com.turnover.my.taste.app.repository.token"])
class RedisRepositoryConfig(

    @Value("\${spring.data.redis.host}")
    private val host: String,

    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    @Bean
    fun redissonClient(configuration: RedissonConfiguration): RedissonClient {
        return RedissonClientBuilder.build(configuration)
    }

    @Bean
    @Primary
    fun redissonConnectionFactory(redissonClient: RedissonClient): RedisConnectionFactory {
        log.warn { "redissonClient :: ${redissonClient.config.toYAML()}" }
        return RedissonConnectionFactory(redissonClient)
    }

    @Bean
    fun lettuceConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        return RedisTemplate<Any, Any>().apply {
            this.connectionFactory = lettuceConnectionFactory()
        }
    }

//    @Bean
//    fun redisTemplate(redissonConnectionFactory: RedisConnectionFactory): RedisTemplate<*, *> {
//        return RedisTemplate<Any, Any>().apply {
//            this.connectionFactory = redissonConnectionFactory
//        }
//    }
}