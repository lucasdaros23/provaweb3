package com.ecommerce.inventory.repository

import com.ecommerce.inventory.entity.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : JpaRepository<Inventory, Long> {
    fun findByProductId(productId: String): Inventory?
}
