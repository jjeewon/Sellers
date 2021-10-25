package com.example.sellers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
// SpringBoot : 어노테이션에 서버 띄우는데 필요한 코드 다 넣어놨음
@SpringBootApplication
open class SellersApplication

fun main() {
    runApplication<SellersApplication>()
}