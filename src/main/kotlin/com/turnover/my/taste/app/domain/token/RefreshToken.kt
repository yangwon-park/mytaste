package com.turnover.my.taste.app.domain.token

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "RefreshToken", timeToLive = 604800)
class RefreshToken(

    @Id
    val memberId: Long,

    val token: String,
) {

}