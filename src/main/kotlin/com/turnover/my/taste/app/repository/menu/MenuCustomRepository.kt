package com.turnover.my.taste.app.repository.menu

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class MenuCustomRepository(
    val queryFactory: JPAQueryFactory
) {

}