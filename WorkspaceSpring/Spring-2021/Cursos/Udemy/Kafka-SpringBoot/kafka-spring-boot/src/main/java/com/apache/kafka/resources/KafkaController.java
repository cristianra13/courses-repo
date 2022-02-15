package com.apache.kafka.resources;

import com.apache.kafka.domain.service.ProducerClass;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    private final ProducerClass producerClass;

    public KafkaController(ProducerClass producerClass) {
        this.producerClass = producerClass;
    }

    @PostMapping(value = "/publish")
    public String sendmessage(@RequestParam("message") String message) {
        this.producerClass.sendMessage(message);
        return "Published successfully";
    }

    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic("user", 3, (short) 1);
    }
}
