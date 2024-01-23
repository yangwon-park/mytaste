package com.turnover.my.taste.app.domain.embedded

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalTime

@Embeddable
data class BusinessTime(

    @Column(name = "opening_time")
    val openingTime: LocalTime?,

    @Column(name = "closing_time")
    val closingTime: LocalTime?,

    @Column(name = "break_start_time")
    val breakStartTime: LocalTime?,

    @Column(name = "break_end_time")
    val breakEndTime: LocalTime?,

    @Column(name = "last_order_time")
    val lastOrderTime: LocalTime?,
) {
}