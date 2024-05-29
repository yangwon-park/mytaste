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
import java.util.HashMap
import java.util.stream.Collectors

private val log = KotlinLogging.logger { }

/**
 * pp에서 JwtConfig
 */
@Component
class TokenProvider(
    @Value("\${jwt.secret-key}")
    private val secretKey: String,

    @Value("\${jwt.token-validity-in-seconds}")
    private val accessTokenValidity: Long,

    @Value("\${jwt.refresh-token-validity-in-seconds}")
    private val refreshTokenValidity: Long,
) : InitializingBean {

    private final val accessTokenValidityInMilliseconds: Long = accessTokenValidity * 1000
    private final val refreshTokenValidityInMilliseconds: Long = refreshTokenValidity * 1000

    private var key: Key? = null

    // Bean 생성 후 secret 값을 Base64 Decode하여 변수에 할당하기 위함
    override fun afterPropertiesSet() {
        val secretKeyBytes = Decoders.BASE64.decode(secretKey)

        key = Keys.hmacShaKeyFor(secretKeyBytes)
    }

    fun createAccessToken(authentication: Authentication): String {
        val authorities = generateAuthorityString(authentication.authorities)

        return Jwts.builder()
            .setSubject(authentication.name)
            .setHeaderParam(TYP, JWT)
            .setClaims(generateClaims(authorities, ACCESS_TOKEN))
            .setIssuedAt(Date(Date().time))
            .setExpiration(Date(Date().time + accessTokenValidityInMilliseconds))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun createRefreshToken(authentication: Authentication): String {
        val authorities = generateAuthorityString(authentication.authorities)

        return Jwts.builder()
            .setSubject(authentication.name)
            .setHeaderParam(TYP, JWT)
            .setClaims(generateClaims(authorities, REFRESH_TOKEN))
            .setIssuedAt(Date(Date().time))
            .setExpiration(Date(Date().time + refreshTokenValidityInMilliseconds))
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
            log.info { "잘못된 JWT 서명입니다." }
        } catch (e: MalformedJwtException) {
            log.info { "잘못된 JWT 서명입니다." }
        } catch (e: ExpiredJwtException) {
            log.info { "만료된 JWT 토큰입니다." }
        } catch (e: UnsupportedJwtException) {
            log.info { "지원되지 않는 JWT 토큰입니다." }
        } catch (e: IllegalArgumentException) {
            log.info { "JWT 토큰이 잘못되었습니다." }
        }

        return false
    }

    private fun generateClaims(
        authority: String,
        tokenType: String,
    ): HashMap<String, Any> {
        val claims = HashMap<String, Any>()
        claims[AUTHORITIES_KEY] = authority
        claims[TOKEN_TYPE_KEY] = tokenType

        return claims
    }

    private fun generateAuthorityString(authorities: MutableCollection<out GrantedAuthority>): String {
        return authorities
            .stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.joining("|"))
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
        private const val TOKEN_TYPE_KEY = "type"
        private const val ACCESS_TOKEN = "at"
        private const val REFRESH_TOKEN = "rt"
        private const val TYP = "typ"
        private const val JWT = "JWT"
    }
}
