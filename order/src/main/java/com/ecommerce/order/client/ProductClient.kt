package com.ecommerce.order.client

import com.ecommerce.order.dto.ProductDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class ProductClient(
    @Value("\${services.product.url}")
    private val productServiceUrl: String,
    private val restTemplate: RestTemplate
) {

    fun getProductById(productId: String): ProductDTO {
        val url = "$productServiceUrl/products/{id}"
        return try {
            restTemplate.getForObject(url, ProductDTO::class.java, productId)!!
        } catch (e: HttpClientErrorException.NotFound) {
            throw RuntimeException("Produto não encontrado: $productId")
        }
    }
}
