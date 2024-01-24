package com.turnover.my.taste.app.service.store

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

    @Transactional(rollbackFor = [Exception::class])
    fun saveStore(request: StoreDTO.Save): Long? {
        val entity = storeRepository.save(request.toEntity())

        return entity.id
    }

    fun getStores(): List<StoreDTO.SearchResult> {
        return storeCustomRepository.getStores()
    }
}