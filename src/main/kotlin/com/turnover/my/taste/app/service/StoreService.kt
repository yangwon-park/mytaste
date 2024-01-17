package com.turnover.my.taste.app.service

import com.turnover.my.taste.app.repository.store.StoreCustomRepository
import com.turnover.my.taste.app.repository.store.StoreRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class StoreService(
    val storeRepository: StoreRepository,
    val storeCustomRepository: StoreCustomRepository,
) {

}