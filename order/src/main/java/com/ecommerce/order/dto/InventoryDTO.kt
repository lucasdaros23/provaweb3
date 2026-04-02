package com.ecommerce.order.dto

data class InventoryDTO(
    var id: String? = null,
    var productId: String,
    var quantity: Int
)
