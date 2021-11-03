package com.gomdolstudio.sellers.di

import com.gomdolstudio.sellers.data.service.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    @Reusable
    fun provideHelloRetrofitService(@Named("normal") retrofit: Retrofit): HelloService {
        return retrofit.create(HelloService::class.java)
    }
    @Provides
    @Reusable
    fun provideSignupRetrofitService(@Named("normal") retrofit: Retrofit): SignupService {
        return retrofit.create(SignupService::class.java)
    }
    @Provides
    @Reusable
    fun provideSigninRetrofitService(@Named("normal") retrofit: Retrofit): SigninService {
        return retrofit.create(SigninService::class.java)
    }
    @Provides
    @Reusable
    fun provideImageUploadRetrofitService(@Named("registration") retrofit: Retrofit): ProductImageUploadService {
        return retrofit.create(ProductImageUploadService::class.java)
    }

    @Provides
    @Reusable
    fun provideProductRegistrationRetrofitService(@Named("registration") retrofit: Retrofit): ProductRegisterService {
        return retrofit.create(ProductRegisterService::class.java)
    }

    @Provides
    @Reusable
    fun provideRefreshRetrofitService(@Named("registration") retrofit: Retrofit): RefreshService{
        return retrofit.create(RefreshService::class.java)
    }

}

