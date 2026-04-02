package com.ecommerce.order.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentResponseDTO(
    var id: String? = null,
    var orderId: String,
    var amount: BigDecimal,
    var status: String,
    var createdAt: LocalDateTime
)
