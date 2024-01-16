package com.turnover.my.taste.app.core.datasource

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appTransactionManager",
    basePackages = ["com.turnover.my.taste.app.repository"]
)
class AppDataSourceConfig {

    @Bean
    fun entityManagerFactoryBuilder(): EntityManagerFactoryBuilder {
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), HashMap<String, Any?>(), null)
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.app")
    fun appDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    fun appDataSource(
        @Qualifier("appDataSourceProperties") appDataSourceProperties: DataSourceProperties
    ): DataSource {
        return appDataSourceProperties.initializeDataSourceBuilder().build()
    }

    @Primary
    @Bean
    fun appTransactionManager(
        @Qualifier("appEntityManagerFactory") appEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(appEntityManagerFactory)
    }

    @Primary
    @Bean
    fun appEntityManagerFactory(
        @Qualifier("appDataSource") appDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(appDataSource)
            .packages("com.turnover.my.taste.app.domain")
            .persistenceUnit("appEntityManager")
            .build();
    }
}