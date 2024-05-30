package com.turnover.my.taste.app.domain.member.dto

data class TokenDTO(
    val accessToken: String? = null,
    val refreshToken: String? = null,
) {

}