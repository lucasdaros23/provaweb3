package com.ecommerce.user.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    var id: Long? = null,
    var nome: String,
    @Column(unique = true)
    var email: String
)
