package com.example.sellers.domain.product

import com.example.sellers.domain.jpa.BaseEntity
import java.util.*
import javax.persistence.*

@Entity(name = "product_image")
class ProductImage (
    var path: String,
    var productId: Long? = null
): BaseEntity(){}

