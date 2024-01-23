package com.turnover.my.taste.app.domain.member.dto

import com.turnover.my.taste.app.domain.member.Member
import java.time.LocalDateTime

class MemberDTO {

    data class JoinRequest(

        val snsId: String,

        val pushToken: String,

        val nickname: String,

        val joinRoute: String,

        val duplicateInfo: String,
    ) {

        fun toEntity(): Member {
            return Member(
                snsId = snsId,
                duplicateInfo = duplicateInfo,
                nickname = nickname,
                joinRoute = joinRoute,
                lastLoginTime = LocalDateTime.now()
            )
        }
    }
}