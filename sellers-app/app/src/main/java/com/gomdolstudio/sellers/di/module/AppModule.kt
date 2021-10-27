package com.gomdolstudio.sellers.di.module

import com.gomdolstudio.covidinfoapp.di.ViewModelModule
import com.gomdolstudio.sellers.di.RetrofitModule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class), (RetrofitModule::class)])
class AppModule {
    @Singleton
    @Provides
    internal fun provideRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}