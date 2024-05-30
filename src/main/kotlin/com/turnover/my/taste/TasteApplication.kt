package com.turnover.my.taste

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
class TasteApplication

fun main(args: Array<String>) {
	runApplication<TasteApplication>(*args)
}
