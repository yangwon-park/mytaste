package com.turnover.my.taste.app.domain.login.dto

import com.turnover.my.taste.app.domain.login.enums.JoinRoute

class LoginDTO {

    data class SnsInfo(
        val snsId: String,
        val jointRoute: JoinRoute,
        val pushToken: String,
    ) {

    }

    data class LoginInfo(
        val memberId: Long,
    )
}