package com.gomdolstudio.sellers.api.request

import android.util.Patterns

class SigninRequest (
        val email: String?,
        val password: String?
) {
    fun isNotValidEmail() =
            email.isNullOrBlank()
                    || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword() =
            password.isNullOrBlank()
                    || password.length !in 8..20
}