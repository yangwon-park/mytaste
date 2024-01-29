package com.turnover.my.taste.app.advice

import com.turnover.my.taste.app.controller.common.CustomErrorResponse
import com.turnover.my.taste.app.exception.DuplicateException
import com.turnover.my.taste.app.exception.EntityNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFoundExceptionHandler(e: EntityNotFoundException): CustomErrorResponse {

        logger.error { e.message }

        return CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.message)
    }

    @ExceptionHandler(DuplicateException::class)
    fun duplicateNicknameExceptionHandler(e: DuplicateException): CustomErrorResponse {
        return CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.message)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(e: Exception): CustomErrorResponse {
        return CustomErrorResponse(SC_INTERNAL_SERVER_ERROR, e.message)
    }
}