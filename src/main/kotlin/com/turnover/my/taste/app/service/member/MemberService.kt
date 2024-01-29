package com.turnover.my.taste.app.service.member

import com.turnover.my.taste.app.domain.member.dto.MemberDTO
import com.turnover.my.taste.app.repository.member.MemberPersonalRepository
import com.turnover.my.taste.app.repository.member.MemberRepository
import com.turnover.my.taste.app.repository.member.MemberSnsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MemberService(
    val memberRepository: MemberRepository,
    val memberSnsRepository: MemberSnsRepository,
    val memberPersonalRepository: MemberPersonalRepository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun signUpMember(request: MemberDTO.SignUpRequest): Long? {
        val signedUpMember = memberRepository.save(request.toMemberEntity())

        val signedUpMemberSns = memberSnsRepository.save(request.toMemberSnsEntity())

        val signedUpMemberPersonal = memberPersonalRepository.save(request.toMemberPersonalEntity())

        signedUpMember.linkPersonalAndSns(signedUpMemberSns, signedUpMemberPersonal)

        return signedUpMember.id
    }
}