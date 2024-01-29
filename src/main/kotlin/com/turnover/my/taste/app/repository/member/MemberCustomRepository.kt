package com.turnover.my.taste.app.repository.member

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MemberCustomRepository(
    val queryFactory: JPAQueryFactory,
) {

}