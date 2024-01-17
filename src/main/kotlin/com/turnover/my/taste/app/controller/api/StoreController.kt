package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/store")
class StoreController(
    val storeService: StoreService,
) {

    @GetMapping
    fun initProject() {
        println("ㅁㅇㄹ하ㅓㅣ무을히ㅏㅁㅇ르힘ㅇㄹ흼앓")
    }
}