package com.turnover.my.taste.app.service.menu

import com.turnover.my.taste.app.domain.menu.dto.MenuDTO
import com.turnover.my.taste.app.exception.EntityNotFoundException
import com.turnover.my.taste.app.repository.menu.MenuCustomRepository
import com.turnover.my.taste.app.repository.menu.MenuRepository
import com.turnover.my.taste.app.repository.store.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MenuService(
    val menuRepository: MenuRepository,
    val menuCustomRepository: MenuCustomRepository,
    val storeRepository: StoreRepository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun saveMenu(request: MenuDTO.Save): Long? {
        val savedMenu = menuRepository.save(request.toEntity())

        val store = storeRepository.findByIdOrNull(request.storeId)
            ?: throw EntityNotFoundException("매장", request.storeId)

        store.linkMenu(savedMenu)

        return savedMenu.id
    }

}