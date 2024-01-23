package com.turnover.my.taste.app.service

import com.turnover.my.taste.app.domain.store.Store
import com.turnover.my.taste.app.domain.store.dto.StoreDTO
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

    fun getAllStore(): MutableList<Store> {
        return storeRepository.findAll();
    }

    @Transactional(rollbackFor = [Exception::class])
    fun saveStore(saveRequest: StoreDTO.Save): Long? {
        val entity = storeRepository.save(saveRequest.toEntity())

        println("entity = ${entity}")

        return entity.id
    }
}