package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.request.SigninRequest
import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.SigninResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SigninService {
    @POST("/api/v1/signin")
    fun signup(@Body signinRequest: SigninRequest) : Single<ApiResponse<SigninResponse>>
}