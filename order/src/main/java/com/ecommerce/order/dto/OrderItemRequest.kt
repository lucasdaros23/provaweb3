package com.ecommerce.order.dto

data class OrderItemRequest(
    var productId: String,
    var quantity: Int
)
