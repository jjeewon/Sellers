package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("/api/v1/users")
    fun signup(@Body signupRequest: SignupRequest) : Single<ApiResponse<Void>>
}