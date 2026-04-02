package com.ecommerce.payment.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var orderId: String,
    var amount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var status: PaymentStatusEnum,
    var createdAt: LocalDateTime
)
