package com.gomdolstudio.sellers

import com.gomdolstudio.sellers.data.service.HelloService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    lateinit var helloService: HelloService

    @Before
    fun 데이터서비스세팅(){
        helloService = Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HelloService::class.java)
    }

    @Test
    fun 헬로데이터테스트(){
        helloService.getWorld().subscribe(System.out::println)
    }
}