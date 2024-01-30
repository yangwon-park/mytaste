package com.turnover.my.taste.app.repository.menu

import com.turnover.my.taste.app.domain.menu.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long> {
}