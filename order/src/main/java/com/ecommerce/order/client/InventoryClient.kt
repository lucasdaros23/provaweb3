package com.ecommerce.order.client

import com.ecommerce.order.dto.DecreaseStockRequest
import com.ecommerce.order.dto.InventoryDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class InventoryClient(
    @Value("\${services.inventory.url}")
    private val inventoryServiceUrl: String,
    private val restTemplate: RestTemplate
) {

    fun getInventoryByProductId(productId: String): InventoryDTO {
        val url = "$inventoryServiceUrl/inventory/$productId"
        val inventory = restTemplate.getForObject(url, InventoryDTO::class.java)
            ?: throw RuntimeException("Estoque não encontrado para produto: $productId")
        return inventory
    }

    fun decreaseStock(productId: String, quantity: Int) {
        val url = "$inventoryServiceUrl/inventory/$productId/decrease"
        val request = DecreaseStockRequest(quantity)
        restTemplate.postForObject(url, request, Void::class.java)
    }
}
