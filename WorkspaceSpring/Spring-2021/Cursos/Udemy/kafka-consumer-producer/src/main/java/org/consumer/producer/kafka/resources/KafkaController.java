package org.consumer.producer.kafka.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.consumer.producer.kafka.domain.models.MoreSimpleKafkaModel;
import org.consumer.producer.kafka.domain.models.SimpleModelKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper jsonConverter;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper jsonConverter) {
        this.kafkaTemplate = kafkaTemplate;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping
    public void post(@RequestBody SimpleModelKafka modelKafka) throws JsonProcessingException {
        kafkaTemplate.send("mytopic", jsonConverter.writeValueAsString(modelKafka));
    }

    @PostMapping("/v2")
    public void post(@RequestBody MoreSimpleKafkaModel simpleKafkaModel) throws JsonProcessingException {
        kafkaTemplate.send("mytopic2", jsonConverter.writeValueAsString(simpleKafkaModel));
    }

    /**
     * Método que actua como un listener
     * @param simpleModelKafka
     */
    @KafkaListener(topics = "mytopic")
    public void getFromKafka(String simpleModelKafka) throws JsonProcessingException {
        System.out.println(simpleModelKafka);
        SimpleModelKafka modelKafka = jsonConverter.readValue(simpleModelKafka, SimpleModelKafka.class);
        System.out.println(modelKafka.toString());
    }

    @KafkaListener(topics = "mytopic2")
    public void getFromkafkaTopic2(String moreSimpleKafkaModel) throws JsonProcessingException {
        System.out.println(moreSimpleKafkaModel);
        MoreSimpleKafkaModel moreSimpleKafkaMod = jsonConverter.readValue(moreSimpleKafkaModel, MoreSimpleKafkaModel.class);
        System.out.println(moreSimpleKafkaMod.toString());
    }

}
