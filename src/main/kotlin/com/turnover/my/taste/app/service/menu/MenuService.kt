package com.turnover.my.taste.app.service.menu

import com.turnover.my.taste.app.domain.menu.dto.MenuDTO
import com.turnover.my.taste.app.exception.EntityNotFoundException
import com.turnover.my.taste.app.repository.menu.MenuRepository
import com.turnover.my.taste.app.repository.store.StoreRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MenuService(
    val menuRepository: MenuRepository,
    val menuCusRepository: MenuRepository,
    val storeRepository: StoreRepository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun saveMenu(request: MenuDTO.Save): Long? {
        val savedMenu = menuRepository.save(request.toEntity())

        val store = storeRepository.findById(request.storeId)
            ?: throw EntityNotFoundException("매장", request.storeId)


        return 123L
    }

}