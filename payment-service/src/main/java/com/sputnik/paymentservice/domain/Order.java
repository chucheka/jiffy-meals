package com.sputnik.paymentservice.domain;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record Order(
        @NotNull
        Long orderId,

        @NotNull
        Long customerId,
        @NotNull
        BigDecimal price

//        List<OrderItem> orderItems
) {
}