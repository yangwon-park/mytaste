package com.turnover.my.taste.app.core.config.security

import com.turnover.my.taste.app.core.jwt.JwtFilter
import com.turnover.my.taste.app.core.jwt.TokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(
    private val tokenProvider: TokenProvider,
): SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
            .addFilterBefore(
                JwtFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }
}