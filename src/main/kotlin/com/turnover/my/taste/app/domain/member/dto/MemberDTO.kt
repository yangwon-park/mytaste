package com.turnover.my.taste.app.domain.member.dto

import com.turnover.my.taste.app.domain.member.Member
import com.turnover.my.taste.app.domain.member.MemberPersonal
import com.turnover.my.taste.app.domain.member.MemberSns
import com.turnover.my.taste.app.domain.member.enums.Gender
import com.turnover.my.taste.app.domain.member.enums.MemberStatus
import com.turnover.my.taste.app.domain.member.enums.SnsKind
import java.time.LocalDate

class MemberDTO {

    data class LoginRequest(
        val username: String,
    ) {

    }

    data class SignUpRequest(

        val status: MemberStatus = MemberStatus.ACTIVE,

        val nickname: String,

        val intro: String,

        val profileImageUrl: String,

        val snsId: String,

        val snsKind: SnsKind,

        val phoneNumber: String,

        val birthDate: LocalDate,

        val gender: Gender,
    ) {

        fun toMemberEntity(): Member {
            return Member(
                status = status,
                nickname = nickname,
                intro = intro,
                profileImageUrl = profileImageUrl
            )
        }

        fun toMemberSnsEntity(): MemberSns {
            return MemberSns(
                snsId = snsId,
                snsKind = snsKind
            )
        }

        fun toMemberPersonalEntity(): MemberPersonal {
            return MemberPersonal(
                phoneNumber = phoneNumber,
                birthDate = birthDate,
                gender = gender
            )
        }
    }
}