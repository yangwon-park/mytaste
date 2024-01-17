package com.turnover.my.taste.app.controller.common

data class ResultResponse<T>(
    val count: Int,
    val data: T,
) {

    constructor(data: T) : this(0, data)
}