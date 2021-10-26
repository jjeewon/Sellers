package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HelloService {
    @GET("/api/v1/hello")
    fun getWorld(): Single<ApiResponse<String>>
}