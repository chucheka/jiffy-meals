package com.sputnik.paymentservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sputnik.paymentservice.domain.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventsService {


    private final ObjectMapper objectMapper;

    private final KafkaTemplate<Integer,String> kafkaTemplate;


    public void processOrderEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {

        OrderEvent orderEvent = objectMapper.readValue(consumerRecord.value(), OrderEvent.class);

        log.info("THE ORDER EVENT : {} ", orderEvent);
    }

    public void handleRecovery(ConsumerRecord<Integer,String> record){

        Integer key = record.key();
        String message = record.value();

        var listenableFuture = kafkaTemplate.sendDefault(key, message);
        listenableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                handleFailure(key, message, throwable);
            } else {
                handleSuccess(key, message, sendResult);

            }
        });
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
