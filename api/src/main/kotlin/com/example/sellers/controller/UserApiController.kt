package com.example.sellers.controller

import com.example.sellers.ApiResponse
import com.example.sellers.domain.auth.SignUpRequest
import com.example.sellers.domain.auth.SignupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserApiController @Autowired constructor (
    private val signupService: SignupService
        ){
    
    @PostMapping("/users")
    fun signup(@RequestBody signUpRequest: SignUpRequest) =
        ApiResponse.ok(signupService.signup(signUpRequest))
}