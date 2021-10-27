package com.gomdolstudio.sellers.di

import com.gomdolstudio.sellers.data.service.HelloService
import com.gomdolstudio.sellers.data.service.SignupService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class RetrofitModule {
    @Provides
    @Reusable
    fun provideHelloRetrofitService(retrofit: Retrofit): HelloService {
        return retrofit.create(HelloService::class.java)
    }
    @Provides
    @Reusable
    fun provideSignupRetrofitService(retrofit: Retrofit): SignupService {
        return retrofit.create(SignupService::class.java)
    }

}

