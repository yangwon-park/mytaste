package com.turnover.my.taste.app.service.credential

import com.turnover.my.taste.app.domain.member.Member
import com.turnover.my.taste.app.domain.member.enums.MemberStatus
import com.turnover.my.taste.app.exception.EntityNotFoundException
import com.turnover.my.taste.app.repository.member.MemberCustomRepository
import com.turnover.my.taste.app.repository.member.MemberRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

private val logger = KotlinLogging.logger {}

class CredentialService(
    private val memberRepository: MemberRepository,
    private val memberCustomRepository: MemberCustomRepository,
): UserDetailsService {

    override fun loadUserByUsername(nickname: String): UserDetails {
        val loginMember = memberCustomRepository.findByNickname(nickname)
            ?: throw EntityNotFoundException("회원", nickname)

        return createUserDetails(loginMember)
    }

    private fun createUserDetails(loginMember: Member): UserDetails {
        if (loginMember.status !== MemberStatus.ACTIVE) {
            logger.info { "비활성화 유저 접근. ID :: ${loginMember.id}" }

            throw RuntimeException("비활성화 유저")
        }

        val authorities: MutableSet<GrantedAuthority> = HashSet()
        val simpleGrantedAuthority = SimpleGrantedAuthority("USER")
        authorities.add(simpleGrantedAuthority)

        return User(loginMember.nickname, "1234", authorities)
    }
}