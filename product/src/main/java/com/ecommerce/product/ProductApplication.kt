package com.ecommerce.product

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ProductApplication

fun main(args: Array<String>) {
    SpringApplication.run(ProductApplication::class.java, *args)
}
