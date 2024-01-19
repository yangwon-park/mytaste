package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/store")
class StoreController(
    val storeService: StoreService,
) {

    @GetMapping
    fun initProject() {
        val size = storeService.getAllStore().size

        println("size = ${size}")
    }

//    @PostMapping
//    fun saveStore(@RequestBody StoreDTO.Save() request): Long {
//
//    }
}