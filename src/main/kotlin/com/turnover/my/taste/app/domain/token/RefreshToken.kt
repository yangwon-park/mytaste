package com.turnover.my.taste.app.domain.token

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "RefreshToken", timeToLive = 604800)
class RefreshToken(

    @Id
    val memberId: Long,

    val token: String,
) {

}