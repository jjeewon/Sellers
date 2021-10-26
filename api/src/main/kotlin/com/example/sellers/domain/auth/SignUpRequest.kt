package com.example.sellers.domain.auth

data class SignUpRequest(
    val email: String,
    val name: String,
    val password: String
)