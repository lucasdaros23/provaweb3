package com.ecommerce.product.services

import com.ecommerce.product.entity.Product
import com.ecommerce.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun findById(id: String): Product {
        return productRepository.findById(id.toLong()).orElseThrow { RuntimeException("Nenhum produto encontrado com o id $id") }
    }

    fun create(product: Product): Product {
        return productRepository.save(product)
    }
}
