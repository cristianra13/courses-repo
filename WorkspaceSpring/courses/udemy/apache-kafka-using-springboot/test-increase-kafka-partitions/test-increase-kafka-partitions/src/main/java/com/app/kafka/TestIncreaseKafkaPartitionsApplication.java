package com.app.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class TestIncreaseKafkaPartitionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestIncreaseKafkaPartitionsApplication.class, args);
  }

}
