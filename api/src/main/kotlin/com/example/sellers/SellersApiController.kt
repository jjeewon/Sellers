package com.example.sellers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
// 빈 만들어주는 어노테이션

@RestController
class SellersApiController {

    @GetMapping("/api/v1/hello")
    fun hello() = ApiResponse.ok("world")
}

