package com.example.sellers.domain.user

import java.util.*
import javax.persistence.*

@Entity
class User (
    val email: String,
    var passWord: String,
    var name: String
    ){
    
}

