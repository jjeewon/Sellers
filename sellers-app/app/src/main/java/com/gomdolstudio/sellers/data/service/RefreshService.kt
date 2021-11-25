package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query


interface RefreshService {
    @POST("/api/v1/refresh_token")
    fun getRefreshToken(@Query("grant_type") grantType: String ="refresh_token"): Single<ApiResponse<String>>
}