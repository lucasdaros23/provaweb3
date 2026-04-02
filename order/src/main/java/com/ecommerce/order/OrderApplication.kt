package com.ecommerce.order

import com.ecommerce.order.config.ServicesConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(ServicesConfig::class)
class OrderApplication

fun main(args: Array<String>) {
    SpringApplication.run(OrderApplication::class.java, *args)
}
