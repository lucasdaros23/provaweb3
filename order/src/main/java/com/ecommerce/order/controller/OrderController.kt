package com.ecommerce.order.controller

import com.ecommerce.order.dto.OrderRequest
import com.ecommerce.order.model.Order
import com.ecommerce.order.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): ResponseEntity<*> {
        return try {
            val order = orderService.createOrder(request)
            ResponseEntity.status(HttpStatus.CREATED).body(order)
        } catch (e: RuntimeException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: String): ResponseEntity<Order> {
        val order = orderService.getOrderById(id)
        return if (order != null) {
            ResponseEntity.ok(order)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
