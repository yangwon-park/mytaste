package com.turnover.my.taste.app.advice

import com.turnover.my.taste.app.controller.common.CustomErrorResponse
import com.turnover.my.taste.app.exception.DuplicateException
import jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(DuplicateException::class)
    fun duplicateNicknameExceptionHandler(e: DuplicateException): CustomErrorResponse {
        return CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(e: Exception): CustomErrorResponse {
        return CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.message)
    }
}