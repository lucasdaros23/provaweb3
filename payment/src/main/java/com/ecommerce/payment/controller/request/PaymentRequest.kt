package com.ecommerce.payment.controller.request

import java.math.BigDecimal

data class PaymentRequest(
    var orderId: String,
    var amount: BigDecimal
)
