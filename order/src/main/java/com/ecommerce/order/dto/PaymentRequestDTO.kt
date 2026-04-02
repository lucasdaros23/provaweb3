package com.ecommerce.order.dto

import java.math.BigDecimal

data class PaymentRequestDTO(
    var orderId: String,
    var amount: BigDecimal
)
