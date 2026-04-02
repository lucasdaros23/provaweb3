package com.ecommerce.order.client

import com.ecommerce.order.dto.PaymentRequestDTO
import com.ecommerce.order.dto.PaymentResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PaymentClient(
    @Value("\${services.payment.url}")
    private val paymentServiceUrl: String,
    private val restTemplate: RestTemplate
) {

    fun processPayment(paymentRequest: PaymentRequestDTO): PaymentResponseDTO {
        val url = "$paymentServiceUrl/payments"
        val response = restTemplate.postForObject(url, paymentRequest, PaymentResponseDTO::class.java)
            ?: throw RuntimeException("Erro ao processar pagamento")
        return response
    }
}
