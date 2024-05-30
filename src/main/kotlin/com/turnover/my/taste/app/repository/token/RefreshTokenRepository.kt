package com.turnover.my.taste.app.repository.token

import com.turnover.my.taste.app.domain.token.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

// transaction을 사용하려면 RedisTemplate 사용해야함
interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {
}