package com.turnover.my.taste.app.controller.common

import ch.qos.logback.core.status.ErrorStatus

data class CustomErrorResponse(
    val status: Int,
    val message: String?,
) {

}