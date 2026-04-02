package com.ecommerce.order.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "services")
data class ServicesConfig(
    @Value("\${services.product.url}")
    var productServiceUrl: String = "",
    @Value("\${services.inventory.url}")
    var inventoryServiceUrl: String = "",
    @Value("\${services.payment.url}")
    var paymentServiceUrl: String = ""
)
