package com.turnover.my.taste.app.core.config.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtConfig: JwtConfig,

): OncePerRequestFilter() {
    private val BEARER_PREFIX: String = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        TODO("Not yet implemented")


    }
}