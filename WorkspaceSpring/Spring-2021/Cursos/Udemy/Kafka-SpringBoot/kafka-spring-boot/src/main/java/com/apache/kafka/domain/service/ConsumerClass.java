package com.apache.kafka.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerClass {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @KafkaListener(topics = "user")
    public void consume(String message) {
        log.info(String.format("********** Consume just receive the message -> %s", message));
    }
}
