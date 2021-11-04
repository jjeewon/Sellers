package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.request.ProductRegistrationRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductRegisterService {
    @POST("/api/v1/products")
    fun registerProduct(@Body request: ProductRegistrationRequest): Single<ApiResponse<Response<Void>>>
}