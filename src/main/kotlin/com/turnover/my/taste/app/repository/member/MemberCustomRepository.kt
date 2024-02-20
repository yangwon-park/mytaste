package com.turnover.my.taste.app.repository.member

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.turnover.my.taste.app.domain.member.Member
import com.turnover.my.taste.app.domain.member.QMember
import org.springframework.stereotype.Repository

@Repository
class MemberCustomRepository(
    val queryFactory: JPAQueryFactory,
) {

    fun findByNickname(nickname: String): Member? {
        return queryFactory
            .selectFrom(QMember.member)
            .fetchOne()
    }
}