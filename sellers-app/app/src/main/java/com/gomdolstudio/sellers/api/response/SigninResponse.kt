package com.gomdolstudio.sellers.api.response

data class SigninResponse(
        val token: String,
        val refreshToken: String,
        val userName: String,
        val userId: Long
)