package com.gomdolstudio.sellers

import com.gomdolstudio.sellers.api.request.SigninRequest
import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.data.service.HelloService
import com.gomdolstudio.sellers.data.service.SigninService
import com.gomdolstudio.sellers.data.service.SignupService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    lateinit var helloService: HelloService
    lateinit var signupService: SignupService
    lateinit var signinService: SigninService

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

        signinService = Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SigninService::class.java)


    }



    @Test
    fun 회원가입테스트(){
        val request: SignupRequest  = SignupRequest("seller@naver.com","sellerssellers","seller")
        signupService.signup(request).subscribe(System.out::println)
    }

    @Test
    fun 로그인테스트(){
        val request: SigninRequest = SigninRequest("seller@naver.com", "sellerssellers")
        signinService.signin(request).subscribe(System.out::println)
    }
}