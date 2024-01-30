package com.turnover.my.taste.app.domain.menu

import jakarta.persistence.*

@Entity
@Table(name = "menu", schema = "app")
class Menu(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    val id: Long? = null,


) {


}