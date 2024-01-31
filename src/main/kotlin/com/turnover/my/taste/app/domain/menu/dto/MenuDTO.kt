package com.turnover.my.taste.app.domain.menu.dto

import com.turnover.my.taste.app.domain.menu.Menu

class MenuDTO {

    data class Save(
        val storeId: Long,

        val name: String,

        val price: Int,

        val intro: String,
    ) {
        fun toEntity(): Menu {
            return Menu(
                name = name,
                price = price,
                isSignature = false,
                intro = intro)
        }
    }
}