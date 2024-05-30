package com.turnover.my.taste.app.core.config.redis.redisson

import com.turnover.my.taste.app.core.config.redis.redisson.RedissonConfiguration.RedisMode.CLUSTER
import com.turnover.my.taste.app.core.config.redis.redisson.RedissonConfiguration.RedisMode.SINGLE
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.codec.JsonJacksonCodec
import org.redisson.config.Config

class RedissonClientBuilder {

    companion object {
        fun build(
            configuration: RedissonConfiguration
        ): RedissonClient {
            val config = Config().setCodec(JsonJacksonCodec())

            val nodes = configuration.nodes

            if (nodes.isNullOrEmpty()) {
                throw IllegalArgumentException("Redisson Configuration Nodes Should Not be Null or Empty")
            }

            when (configuration.mode) {
                SINGLE -> {
                    val singleServer = config.useSingleServer()
                    singleServer.setAddress(nodes[0])

                    if (configuration.password != null) {
                        singleServer.setPassword(configuration.password)
                    }

                    return Redisson.create(config)
                }

                CLUSTER -> {
                    val clusterServers = config.useClusterServers()
                    nodes.forEach(clusterServers::addNodeAddress)

                    if (configuration.password != null) {
                        clusterServers.setPassword(configuration.password)
                    }

                    return Redisson.create(config)
                }

                else -> {
                    throw IllegalArgumentException("Unsupported Redisson mode: ${configuration.mode}")
                }
            }

        }
    }
}