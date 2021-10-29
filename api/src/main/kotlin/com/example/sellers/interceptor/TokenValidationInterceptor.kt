package com.example.sellers.interceptor

import com.example.sellers.domain.auth.UserContextHolder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenValidationInterceptor @Autowired constructor(
    private val userContextHolder: UserContextHolder
) : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return super.preHandle(request, response, handler)
    }
    
    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
        private const val GRANT_TYPE = "grant_type"
        const val GRANT_TYPE_REFRESH = "refresh_token"

        private val DEFAULT_ALLOWED_API_URLS = listOf(
            "POST" to "/api/v1/signin",
            "POST" to "/api/v1/users"
        )
    }
}