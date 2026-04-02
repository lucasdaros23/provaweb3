package com.ecommerce.order.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var productId: String,
    var productName: String,
    var quantity: Int,
    var price: BigDecimal,
    var subtotal: BigDecimal,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null
)
