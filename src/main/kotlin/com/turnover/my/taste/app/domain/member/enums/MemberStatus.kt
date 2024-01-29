package com.turnover.my.taste.app.domain.member.enums

enum class MemberStatus(
    val description: String,
) {
    
    ACTIVE("활동중"),
    LAPSED("탈퇴"),
    SUSPENDED("정지")
}