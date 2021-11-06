package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListService {
    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("productId") productId: Long,
        @Query("categoryId") categoryId: Int?,
        @Query("direction") direction: String,
    ): ApiResponse<List<ProductListItemResponse>>
}