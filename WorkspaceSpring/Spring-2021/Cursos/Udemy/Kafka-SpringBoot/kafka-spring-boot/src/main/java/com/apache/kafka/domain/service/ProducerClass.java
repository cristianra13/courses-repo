package com.apache.kafka.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerClass {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private static final String TOPIC = "user";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        log.info(String.format("********** Producer sending the message now -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }

}
