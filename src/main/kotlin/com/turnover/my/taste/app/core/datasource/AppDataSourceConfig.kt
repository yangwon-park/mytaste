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
class AppDataSourceConfig(

) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.app")
    fun appDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    fun appDataSource(@Qualifier("appDataSourceProperties") appDataSourceProperties: DataSourceProperties ): DataSource {
        return appDataSourceProperties.initializeDataSourceBuilder().build()
    }
}