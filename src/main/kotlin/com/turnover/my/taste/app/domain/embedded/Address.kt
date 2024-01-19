package com.turnover.my.taste.app.domain.embedded

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(

    @Column(name = "road_addr")
    val roadAddr: String,

    @Column(name = "number_addr")
    val numberAddr: String,

    val zipcode: String,

    @Column(name = "detail_addr")
    val detailAddr: String,
)