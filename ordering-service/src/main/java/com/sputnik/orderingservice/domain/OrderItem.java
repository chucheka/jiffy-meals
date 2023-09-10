package com.sputnik.orderingservice.domain;

import java.math.BigDecimal;

public record OrderItem(Long itemId,
                        BigDecimal price,
                        String name
) {
}
