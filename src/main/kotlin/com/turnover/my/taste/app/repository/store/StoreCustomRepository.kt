package com.turnover.my.taste.app.repository.store

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
//import com.turnover.my.taste.app.domain.store.QStore
//import com.turnover.my.taste.app.domain.store.QStore.*
import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import org.springframework.stereotype.Repository

@Repository
class StoreCustomRepository(
    val queryFactory: JPAQueryFactory
) {


    fun getStores(): List<StoreDTO.SearchResult> {
        return emptyList()

//        return queryFactory
//            .select(
//                Projections.constructor(
//                    StoreDTO.SearchResult::class.java,
//                    store.name,
//                    store.lat,
//                    store.lon
//            ))
//            .from(store)
//            .fetch();
    }
}