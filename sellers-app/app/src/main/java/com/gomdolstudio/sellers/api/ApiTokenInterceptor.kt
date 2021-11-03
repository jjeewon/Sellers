package com.gomdolstudio.sellers.api

import android.util.Log
import com.gomdolstudio.sellers.common.Prefs
import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("api","API 요청 ")
        val original = chain.request()
        val request = original.newBuilder().apply {
            Prefs.token?.let { header("Authorization", it) }
            method(original.method, original.body)
        }.build()
        return chain.proceed(request)
    }
}