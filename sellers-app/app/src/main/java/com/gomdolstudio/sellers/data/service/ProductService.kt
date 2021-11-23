package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("/api/v1/products/{id}")
    fun getProduct(@Path("id") id: Long): Single<ApiResponse<ProductResponse>>
}