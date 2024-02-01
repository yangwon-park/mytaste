package com.turnover.my.taste.app.core.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class QueryDslConfig(

    @PersistenceContext(name = "appEntityManager", unitName = "appEntityManager")
    private val appEntityManager: EntityManager,

    @PersistenceContext(unitName = "logEntityManager")
    private val logEntityManager: EntityManager,

) {
    @Bean
    @Primary
    fun appJpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(appEntityManager)
    }
}