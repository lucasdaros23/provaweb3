package com.ecommerce.order.dto

data class OrderRequest(
    var userId: String,
    var items: List<OrderItemRequest>
)
