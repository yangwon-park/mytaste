package com.turnover.my.taste.app.repository.store

import com.querydsl.jpa.impl.JPAQueryFactory
import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import org.springframework.stereotype.Repository

@Repository
class StoreCustomRepository(
     val queryFactory: JPAQueryFactory
) {



    fun getStores(): StoreDTO.SearchResult? {
//        queryFactory
//            .select()
//            .from()
//            .fetch();
        return null
    }

}