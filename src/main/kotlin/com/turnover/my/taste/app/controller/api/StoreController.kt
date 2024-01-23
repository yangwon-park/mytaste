package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import com.turnover.my.taste.app.service.StoreService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/store")
class StoreController(
    val storeService: StoreService,
) {

    @PostMapping
    fun saveStore(@RequestBody request: StoreDTO.Save): Long? {
        println("왜 안되노")
        
        return storeService.saveStore(request);
    }
}