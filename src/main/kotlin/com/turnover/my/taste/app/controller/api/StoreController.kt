package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import com.turnover.my.taste.app.service.store.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stores")
class StoreController(
    val storeService: StoreService,
) {

    @GetMapping
    fun getStores(): List<StoreDTO.SearchResult>{
        return storeService.getStores()
    }

    @PostMapping
    fun saveStore(@RequestBody request: StoreDTO.Save): Long? {
        return storeService.saveStore(request);
    }
}