package com.sputnik.orderingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sputnik.orderingservice.domain.OrderEvent;
import com.sputnik.orderingservice.producer.OrderEventProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "api/v1/orders",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderEventProducer orderEventProducer;

    @PostMapping
    public OrderEvent postOrderEvent(@RequestBody @Valid OrderEvent orderEvent) throws JsonProcessingException {

        orderEventProducer.sendOrderEvent(orderEvent);

        return orderEvent;
    }

}
