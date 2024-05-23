package com.turnover.my.taste.app.domain.bookmark

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime

@RedisHash
class Bookmark(
    @Id
    val id: Long,
    val storeName: String,
    val clickDateTime: LocalDateTime
) {

    override fun toString(): String {
        return "Bookmark(id=$id, storeName='$storeName', clickDateTime=$clickDateTime)"
    }
}