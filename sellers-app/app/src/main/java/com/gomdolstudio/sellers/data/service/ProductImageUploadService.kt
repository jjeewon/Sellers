package com.gomdolstudio.sellers.data.service

import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductImageUploadResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProductImageUploadService {
    @Multipart
    @POST("/api/v1/product_images")
    fun uploadProductImage(@Part images: MultipartBody.Part) : Single<ApiResponse<ProductImageUploadResponse>>
}