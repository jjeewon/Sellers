package com.example.sellers.domain.auth

import com.example.sellers.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired

class SignupService @Autowired constructor(private val userRepository: UserRepository) {
}