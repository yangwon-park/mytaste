package com.turnover.my.taste.app.repository.store

import com.turnover.my.taste.app.domain.store.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {
}