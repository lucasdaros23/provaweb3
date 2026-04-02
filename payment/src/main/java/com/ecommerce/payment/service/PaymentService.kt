package com.ecommerce.payment.service

import com.ecommerce.payment.controller.request.PaymentRequest
import com.ecommerce.payment.entity.Payment
import com.ecommerce.payment.entity.PaymentStatusEnum
import com.ecommerce.payment.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    private val random = Random

    fun processPayment(request: PaymentRequest): Payment {
        val payment = Payment(
            orderId = request.orderId,
            amount = request.amount,
            createdAt = LocalDateTime.now(),
            status = if (random.nextInt(100) < 80) PaymentStatusEnum.APROVADO else PaymentStatusEnum.RECUSADO
        )
        return paymentRepository.save(payment)
    }
}
