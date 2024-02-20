package com.turnover.my.taste.app.core.config.security

import com.turnover.my.taste.app.core.jwt.JwtAccessDeniedHandler
import com.turnover.my.taste.app.core.jwt.JwtAuthenticationEntryPoint
import com.turnover.my.taste.app.core.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }

            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
            }

            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            }
//             세션 상태 유지 불필요 => 인증에 JWT 사용
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .formLogin { it.disable() }         // formLogin 불필요
            .httpBasic { it.disable() }         // http 기본 인증 -> Base64
            .with(JwtSecurityConfig(tokenProvider)) {}

        return http.build()
    }
}