package com.turnover.my.taste.app.repository.store

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.turnover.my.taste.app.domain.store.QStore.*
import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import org.springframework.stereotype.Repository

@Repository
class StoreCustomRepository(
    val queryFactory: JPAQueryFactory
) {

    fun getStorePoints(): List<StoreDTO.StorePoint> {
        return queryFactory
            .select(
                Projections.constructor(
                    StoreDTO.StorePoint::class.java,
                    store.lat,
                    store.lon
            ))
            .from(store)
            .fetch();
    }

    fun getStoreLiteDetailsByStoreId(storeId: Long): StoreDTO.LiteDetails? {
        return queryFactory
            .select(
                Projections.constructor(
                    StoreDTO.LiteDetails::class.java,
                    store.name,
                    store.address,
                    store.storeStatus
                ))
            .from(store)
            .where(store.id.eq(storeId))
            .fetchOne()
    }
}