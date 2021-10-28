package com.example.sellers.domain.auth

data class SigninRequest(
    val email: String,
    val password: String
)