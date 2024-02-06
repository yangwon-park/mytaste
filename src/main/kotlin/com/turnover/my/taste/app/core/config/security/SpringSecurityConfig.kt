package com.turnover.my.taste.app.core.config.security

import com.turnover.my.taste.app.core.config.security.jwt.JwtConfig
import com.turnover.my.taste.app.core.config.security.jwt.JwtConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SpringSecurityConfig(
    private val jwtConfig: JwtConfig,
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { it.disable() }         // formLogin 불필요
            .httpBasic { it.disable() }         // http 기본 인증 -> Base64
//             세션 상태 유지 불필요 => 인증에 JWT 사용
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        return http.build()
    }
}