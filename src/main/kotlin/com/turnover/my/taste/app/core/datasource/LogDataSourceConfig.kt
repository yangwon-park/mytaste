package com.turnover.my.taste.app.core.datasource

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class LogDataSourceConfig(

) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.log")
    fun logDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    fun appDataSource(@Qualifier("logDataSourceProperties") logDataSourceProperties: DataSourceProperties ): DataSource {
        return logDataSourceProperties.initializeDataSourceBuilder().build()
    }
}