package com.turnover.my.taste.app.core.config.security.jwt

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}


@Component
class TokenProvider(
    private val jwtConfig: JwtConfig,

    @Value("\${jwt.token-validity-in-sec}")
    private val JWT_TOKEN_VALIDITY: String,

    @Value("\${jwt.refresh-token-validity-in-sec}")
    private val REFRESH_TOKEN_VALIDITY: Long,
): InitializingBean {



    // Bean 생성 후 secret 값을 Base64 Decode하여 변수에 할당하기 위함
    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }


}