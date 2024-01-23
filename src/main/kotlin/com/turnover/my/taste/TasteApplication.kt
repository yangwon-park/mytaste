package com.turnover.my.taste

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TasteApplication

fun main(args: Array<String>) {
	runApplication<TasteApplication>(*args)
}
