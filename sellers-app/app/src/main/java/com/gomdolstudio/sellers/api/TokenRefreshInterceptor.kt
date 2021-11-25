package com.gomdolstudio.sellers.api

import android.util.Log
import com.gomdolstudio.sellers.common.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class TokenRefreshInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("api", "토큰 갱신 요청 ")
        val original = chain.request()
        val request = original.newBuilder().apply {
            Prefs.refreshToken?.let { header("Authorization", it) }
            method(original.method, original.body)
        }.build()

        val response = chain.proceed(request)
        if (response.code == 401) {
            Log.d("api", "401")
        }
        return response
    }
}