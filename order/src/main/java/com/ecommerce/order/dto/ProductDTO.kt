package com.ecommerce.order.dto

import java.math.BigDecimal

data class ProductDTO(
    var id: String? = null,
    var nome: String,
    var descricao: String,
    var preco: BigDecimal
)
