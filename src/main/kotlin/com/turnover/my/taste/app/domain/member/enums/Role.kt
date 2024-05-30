package com.turnover.my.taste.app.domain.member.enums

import com.turnover.my.taste.app.domain.Describable

enum class Role(
    override val description: String
): Describable {

    USER("일반 유저"),
    ADMIN("관리자")
}