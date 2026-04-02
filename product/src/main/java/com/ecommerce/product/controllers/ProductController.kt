package com.ecommerce.product.controllers

import com.ecommerce.product.entity.Product
import com.ecommerce.product.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun criarProduto(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String): ResponseEntity<Product> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id))
    }

    @GetMapping
    fun buscarTodos(): ResponseEntity<List<Product>> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll())
    }
}
