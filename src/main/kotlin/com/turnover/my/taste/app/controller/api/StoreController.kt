package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import com.turnover.my.taste.app.service.store.StoreService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stores")
class StoreController(
    val storeService: StoreService,
) {

    @GetMapping("/points")
    @PreAuthorize("hasAnyRole('USER')")
    fun getStoresPoints(): List<StoreDTO.StorePoint>{
        return storeService.getStorePoints()
    }

    @GetMapping("/{storeId}/lite")
    fun getStoreLiteDetailsByStoreId(@PathVariable storeId: Long): StoreDTO.LiteDetails {
        return storeService.getStoreLiteDetailsByStoreId(storeId)
    }

    @PostMapping
    fun saveStore(@RequestBody request: StoreDTO.Save): Long? {
        return storeService.saveStore(request);
    }
}