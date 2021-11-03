package com.gomdolstudio.sellers.di.module

import com.gomdolstudio.covidinfoapp.di.ViewModelModule
import com.gomdolstudio.sellers.api.ApiTokenInterceptor
import com.gomdolstudio.sellers.api.TokenRefreshInterceptor
import com.gomdolstudio.sellers.di.RetrofitModule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class), (RetrofitModule::class)])
class AppModule {
    @Named("normal")
    @Singleton
    @Provides
    internal fun provideRetrofitService(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder() .addInterceptor(interceptor).connectTimeout(2000L, TimeUnit.SECONDS) .build()
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Named("registration")
    @Singleton
    @Provides
    internal fun provideRegistrationRetrofitService(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder() .addInterceptor(interceptor).addInterceptor(ApiTokenInterceptor()).connectTimeout(2000L, TimeUnit.SECONDS) .build()
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(provideRegistrationClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun provideRegistrationClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(ApiTokenInterceptor())
        }.build()
    }


}