package com.sputnik.orderingservice.domain;

import com.sputnik.orderingservice.enums.OrderEventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record OrderEvent(Integer orderEventId,
                         OrderEventType orderEventType,
                         @NotNull
                         @Valid
                         Order order) {
}
