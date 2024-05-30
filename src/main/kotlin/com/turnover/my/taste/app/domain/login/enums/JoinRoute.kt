package com.turnover.my.taste.app.domain.login.enums

import com.turnover.my.taste.app.domain.Describable

enum class JoinRoute(
    override val description: String
): Describable {
    KAKAO("카카오"),
    NAVER("네이버"), 
    GOOGLE("구글"), 
    FACEBOOK("페이스북"),
    APPLE("애플"),
}