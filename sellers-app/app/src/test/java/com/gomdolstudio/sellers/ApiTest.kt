package com.gomdolstudio.sellers

import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.data.service.HelloService
import com.gomdolstudio.sellers.data.service.SignupService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    lateinit var helloService: HelloService
    lateinit var signupService: SignupService

    @Before
    fun 데이터서비스세팅(){
        helloService = Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HelloService::class.java)

        signupService = Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SignupService::class.java)
    }

    @Test
    fun 헬로데이터테스트(){
        helloService.getWorld().subscribe(System.out::println)
    }

    @Test
    fun 회원가입테스트(){
        val request: SignupRequest  = SignupRequest("seller@naver.com","sellerssellers","seller")
        signupService.signup(request).subscribe(System.out::println)
    }
}