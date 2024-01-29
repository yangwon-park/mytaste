package com.turnover.my.taste.app.service.member

import com.turnover.my.taste.app.domain.member.dto.MemberDTO
import com.turnover.my.taste.app.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MemberService(
    val memberRepository: MemberRepository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun signUpMember(request: MemberDTO.SignUpRequest): Long? {





        return memberRepository.save(request.toMemberEntity()).id
    }
}