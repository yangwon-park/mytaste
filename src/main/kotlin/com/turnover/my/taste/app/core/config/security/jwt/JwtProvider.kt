package com.turnover.my.taste.app.core.config.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

class JwtProvider(
    private val jwtConfig: JwtConfig
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity?) {
        super.configure(builder)
    }
}