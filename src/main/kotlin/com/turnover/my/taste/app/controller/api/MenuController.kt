package com.turnover.my.taste.app.controller.api

import com.turnover.my.taste.app.domain.menu.dto.MenuDTO
import com.turnover.my.taste.app.service.menu.MenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menus")
class MenuController(
    val menuService: MenuService
) {

    @PostMapping
    fun saveMenu(@RequestBody request: MenuDTO.Save): Long? {
        return menuService.saveMenu(request)
    }
}