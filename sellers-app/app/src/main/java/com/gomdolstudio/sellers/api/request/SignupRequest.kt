package com.gomdolstudio.sellers.api.request

import android.util.Patterns

class SignupRequest(
    val email: String?,
    val password: String?,
    val name: String?
) {
    fun isNotValidEmail() =
        email.isNullOrBlank()
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword() =
        password.isNullOrBlank()
                || password.length !in 8..20

    fun isNotValidName() =
        name.isNullOrBlank()
                || name.length !in 2..20
}