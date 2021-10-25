package com.example.sellers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class SellersExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(SellersException::class)
    fun handleSellersException(e: SellersException): ApiResponse{
        logger.error("API error", e.message)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse{
        logger.error("API error", e.message)
        return ApiResponse.error("알 수 없는 오류가 발생했습니다.")
    }
}