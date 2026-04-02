package com.ecommerce.inventory.entity

import jakarta.persistence.*

@Entity
@Table(name = "inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var productId: String,
    var quantity: Int
)
