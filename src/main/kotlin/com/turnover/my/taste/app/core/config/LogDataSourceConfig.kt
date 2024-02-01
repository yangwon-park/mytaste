package com.turnover.my.taste.app.core.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class LogDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.log")
    fun logDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun logDataSource(
        @Qualifier("logDataSourceProperties") logDataSourceProperties: DataSourceProperties
    )
    : DataSource {
        return logDataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Bean
    fun logTransactionManager(
        @Qualifier("logEntityManagerFactory") logEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(logEntityManagerFactory)
    }
    @Bean
    fun logEntityManagerFactory(
        @Qualifier("logDataSource") logDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(logDataSource)
            .packages("com.turnover.my.taste.log.domain")
            .persistenceUnit("logEntityManager")
            .build();
    }
}