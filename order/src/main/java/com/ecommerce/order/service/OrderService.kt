package com.ecommerce.order.service

import com.ecommerce.order.client.InventoryClient
import com.ecommerce.order.client.PaymentClient
import com.ecommerce.order.client.ProductClient
import com.ecommerce.order.dto.OrderItemRequest
import com.ecommerce.order.dto.OrderRequest
import com.ecommerce.order.dto.PaymentRequestDTO
import com.ecommerce.order.dto.PaymentResponseDTO
import com.ecommerce.order.dto.ProductDTO
import com.ecommerce.order.model.Order
import com.ecommerce.order.model.OrderItem
import com.ecommerce.order.model.OrderStatus
import com.ecommerce.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val restTemplate: RestTemplate,
    private val productClient: ProductClient,
    private val inventoryClient: InventoryClient,
    private val paymentClient: PaymentClient
) {

    fun createOrder(request: OrderRequest): Order {
        val orderItems = mutableListOf<OrderItem>()
        var totalAmount = BigDecimal.ZERO

        for (itemRequest in request.items) {
            val product = productClient.getProductById(itemRequest.productId)
            val inventory = inventoryClient.getInventoryByProductId(itemRequest.productId)

            if (inventory.quantity < itemRequest.quantity) {
                throw RuntimeException("Estoque insuficiente para produto: ${product.nome}")
            }

            val subtotal = product.preco.multiply(BigDecimal.valueOf(itemRequest.quantity.toLong()))
            val orderItem = OrderItem(
                productId = product.id!!,
                productName = product.nome,
                quantity = itemRequest.quantity,
                price = product.preco,
                subtotal = subtotal
            )

            orderItems.add(orderItem)
            totalAmount = totalAmount.add(subtotal)
        }

        val order = Order(
            userId = request.userId,
            items = orderItems,
            totalAmount = totalAmount,
            status = OrderStatus.CRIADO,
            createdAt = LocalDateTime.now()
        )

        val savedOrder = orderRepository.save(order)

        val paymentRequest = PaymentRequestDTO(
            orderId = savedOrder.id!!.toString(),
            amount = totalAmount
        )

        val paymentResponse = paymentClient.processPayment(paymentRequest)

        savedOrder.paymentId = paymentResponse.id!!

        if (paymentResponse.status == "APROVADO") {
            savedOrder.status = OrderStatus.PAGO
            for (item in orderItems) {
                inventoryClient.decreaseStock(item.productId, item.quantity)
            }
        } else {
            savedOrder.status = OrderStatus.CANCELADO
        }

        return orderRepository.save(savedOrder)
    }

    fun getOrderById(id: String): Order? {
        return orderRepository.findById(id.toLong()).orElse(null)
    }
}
