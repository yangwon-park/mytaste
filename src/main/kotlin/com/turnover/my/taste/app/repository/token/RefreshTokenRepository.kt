package com.turnover.my.taste.app.repository.token

import com.turnover.my.taste.app.domain.token.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshToken, Long> {
}