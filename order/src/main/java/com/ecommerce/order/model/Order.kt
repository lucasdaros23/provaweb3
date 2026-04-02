package com.ecommerce.order.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var userId: String,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: List<OrderItem> = emptyList(),
    var totalAmount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
    var createdAt: LocalDateTime,
    var paymentId: String? = null
)

