package com.turnover.my.taste.app.core.jwt

import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Arrays
import java.util.Date
import java.util.stream.Collectors

private val logger = KotlinLogging.logger {}

/**
 * pp에서 JwtConfig
 */
@Component
class TokenProvider(
    @Value("\${jwt.secret-key}")
    private val SECRET_KEY: String,

    @Value("\${jwt.token-validity-in-seconds}")
    private val JWT_TOKEN_VALIDITY: Long,

    @Value("\${jwt.refresh-token-validity-in-seconds}")
    private val REFRESH_TOKEN_VALIDITY: Long,
) : InitializingBean {

    private final val tokenValidityInMilliseconds: Long = JWT_TOKEN_VALIDITY * 1000

    private var key: Key? = null

    companion object {
        private const val AUTHORITIES_KEY = "AUTH"
    }

    // Bean 생성 후 secret 값을 Base64 Decode하여 변수에 할당하기 위함
    override fun afterPropertiesSet() {
        val secretKeyBytes = Decoders.BASE64.decode(SECRET_KEY)

        key = Keys.hmacShaKeyFor(secretKeyBytes)
    }

    fun createAccessToken(authentication: Authentication): String {
        val validity = Date(Date().time + tokenValidityInMilliseconds)

        val authorities = authentication.authorities
            .stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.joining("m"))

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val claims = Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        val authorities: Collection<GrantedAuthority> = Arrays
            .stream(
                claims[AUTHORITIES_KEY].toString()
                    .split(",".toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()
            )
            .map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())

        val principal = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun isValidateToken(token: String?): Boolean {
        try {
            Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)

            return true
        } catch (e: SecurityException) {
            logger.info { "잘못된 JWT 서명입니다." }
        } catch (e: MalformedJwtException) {
            logger.info { "잘못된 JWT 서명입니다." }
        } catch (e: ExpiredJwtException) {
            logger.info { "만료된 JWT 토큰입니다." }
        } catch (e: UnsupportedJwtException) {
            logger.info { "지원되지 않는 JWT 토큰입니다." }
        } catch (e: IllegalArgumentException) {
            logger.info { "JWT 토큰이 잘못되었습니다." }
        }

        return false
    }
}