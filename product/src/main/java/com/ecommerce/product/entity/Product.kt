package com.ecommerce.product.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var nome: String,
    var descricao: String,
    var preco: BigDecimal
)
