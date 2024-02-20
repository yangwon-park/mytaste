package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.core.jwt.JwtFilter
import com.turnover.my.taste.app.core.jwt.TokenProvider
import com.turnover.my.taste.app.domain.member.dto.MemberDTO
import com.turnover.my.taste.app.domain.member.dto.TokenDTO
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api")
class AuthController(
    val tokenProvider: TokenProvider,
    val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {

    @PostMapping("/authenticate")
    fun authorize(@RequestBody loginDTO: MemberDTO.LoginRequest): ResponseEntity<TokenDTO>? {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDTO.username, "1234")

        // 이 시점에 loadByUsername 호출
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        
        SecurityContextHolder.getContext().authentication = authentication

        val jwt: String = tokenProvider.createAccessToken(authentication)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer $jwt")

        return ResponseEntity<TokenDTO>(TokenDTO(jwt), httpHeaders, HttpStatus.OK)
    }
}