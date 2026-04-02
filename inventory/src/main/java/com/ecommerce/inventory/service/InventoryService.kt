package com.ecommerce.inventory.service

import com.ecommerce.inventory.entity.Inventory
import com.ecommerce.inventory.repository.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(
    private val inventoryRepository: InventoryRepository
) {

    fun updateInventory(productId: String, quantity: Int): Inventory {
        val existingInventory = getInventoryByProductId(productId)
        return if (existingInventory == null) {
            val inventory = Inventory(productId = productId, quantity = quantity)
            inventoryRepository.save(inventory)
        } else {
            existingInventory.quantity = quantity
            inventoryRepository.save(existingInventory)
        }
    }

    fun decreaseInventory(productId: String, quantity: Int): Boolean {
        val existingInventory = getInventoryByProductId(productId)
        return if (existingInventory != null && existingInventory.quantity >= quantity) {
            existingInventory.quantity -= quantity
            inventoryRepository.save(existingInventory)
            true
        } else {
            false
        }
    }

    fun getInventoryByProductId(productId: String): Inventory? {
        return inventoryRepository.findByProductId(productId)
    }
}
