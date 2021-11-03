package com.gomdolstudio.sellers.product.registration

import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductImageUploadResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProductImageUploader {
    suspend fun upload(imageFile: File) = try{
        val part = makeImagePart(imageFile)
        withContext(Dispatchers.IO) {

        }
    }catch (e: Exception){
        ApiResponse.error<ProductImageUploadResponse>(
            "알 수 없는 오류가 발생했습니다. "
        )
    }

    private fun makeImagePart(imageFile: File): MultipartBody.Part {
        val mediaType = "multipart/form-data".toMediaTypeOrNull()
        val body = imageFile.asRequestBody(mediaType)
        return MultipartBody.Part
            .createFormData("image", imageFile.name, body)
    }
}