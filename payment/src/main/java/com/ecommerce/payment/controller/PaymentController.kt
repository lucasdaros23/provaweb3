package com.ecommerce.payment.controller

import com.ecommerce.payment.controller.request.PaymentRequest
import com.ecommerce.payment.entity.Payment
import com.ecommerce.payment.service.PaymentService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping
    @Operation(summary = "Processa o pagamento de um pedido")
    fun processPayment(@RequestBody request: PaymentRequest): ResponseEntity<Payment> {
        val payment = paymentService.processPayment(request)
        return ResponseEntity.ok(payment)
    }
}
