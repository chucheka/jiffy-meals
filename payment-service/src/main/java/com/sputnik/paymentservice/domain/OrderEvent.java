package com.sputnik.paymentservice.domain;

import com.sputnik.paymentservice.enums.OrderEventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record OrderEvent(Integer orderEventId,
                         OrderEventType orderEventType,
                         @NotNull
                         @Valid
                         Order order) {
}
