package com.turnover.my.taste.app.core.jwt

import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils

private val log = KotlinLogging.logger {}

class JwtFilter(
    private val tokenProvider: TokenProvider,

) : OncePerRequestFilter() {

    // 토큰의 인증 정보를 SecurityContext에 저장하는 역할 수행
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = getTokenFromRequestHeader(request)
        val requestURI = request.requestURI

        if (StringUtils.hasText(jwt) && tokenProvider.isValidateToken(jwt)) {
            val authentication = tokenProvider.getAuthentication(jwt)

            SecurityContextHolder.getContext().authentication = authentication

            log.debug { "Security Context에 '$authentication.name' 인증 정보 저장 완료. URI: $requestURI" }
        } else {
            log.debug { "유효한 JWT가 없습니다. URI: $requestURI" }
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromRequestHeader(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(7)
        } else null
    }

    companion object {
        private const val BEARER_PREFIX: String = "Bearer "
    }
}