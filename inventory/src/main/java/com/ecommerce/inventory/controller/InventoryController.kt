package com.ecommerce.inventory.controller

import com.ecommerce.inventory.controller.request.InventoryUpdateRequest
import com.ecommerce.inventory.entity.Inventory
import com.ecommerce.inventory.service.InventoryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inventory")
class InventoryController(
    private val inventoryService: InventoryService
) {

    @GetMapping("/{productId}")
    @Operation(summary = "Consultar estoque de um produto")
    fun getInventory(@PathVariable productId: String): ResponseEntity<Inventory> {
        val inventory = inventoryService.getInventoryByProductId(productId)
        return if (inventory != null) {
            ResponseEntity.ok(inventory)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Atualiza a quantidade em estoque de um produto")
    fun updateProductInventory(@PathVariable productId: String, @RequestBody request: InventoryUpdateRequest): ResponseEntity<Inventory> {
        val inventory = inventoryService.updateInventory(productId, request.quantity)
        return ResponseEntity.ok(inventory)
    }

    @PostMapping("/{productId}/decrease")
    @Operation(summary = "Dar baixa no estoque após confirmaçã de pedido")
    fun decreaseStock(@PathVariable productId: String, @RequestBody request: InventoryUpdateRequest): ResponseEntity<String> {
        val success = inventoryService.decreaseInventory(productId, request.quantity)
        return if (!success) {
            ResponseEntity.badRequest().body("Não foi possível dar baixa no produto.")
        } else {
            ResponseEntity.ok("Baixa conclúida!")
        }
    }
}
