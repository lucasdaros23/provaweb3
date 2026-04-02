package com.ecommerce.inventory

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class InventoryApplication

fun main(args: Array<String>) {
    SpringApplication.run(InventoryApplication::class.java, *args)
}
