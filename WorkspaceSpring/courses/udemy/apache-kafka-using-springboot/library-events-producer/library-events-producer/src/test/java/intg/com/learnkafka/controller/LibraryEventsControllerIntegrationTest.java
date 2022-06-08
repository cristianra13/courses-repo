package com.learnkafka.controller;

import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// add embedded kafka to integration tests
@EmbeddedKafka(topics = {"library-events"}, partitions = 3)
// Override kafka configurations
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
  "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class LibraryEventsControllerIntegrationTest {

  private final String PATH = "/v1/libraryevent";
  private final String TOPIC = "library-events";

  @Autowired
  private TestRestTemplate testRestTemplate;

  private Consumer<Integer, String> consumer;

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @BeforeEach
  public void setup() {
    Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));

    consumer = new DefaultKafkaConsumerFactory<>(configs, new IntegerDeserializer(), new StringDeserializer()).createConsumer();
    embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);
  }

  @AfterEach
  void tearDown() {
    consumer.close();
  }

  @Test
  @Timeout(5)
  public void postLibraryEvent() throws InterruptedException {
    // given
    Book book = Book.builder()
      .bookId(456)
      .bookAuthor("Cristian Real")
      .bookName("kafka Using Spring Boot")
      .build();

    LibraryEvent libraryEvent = LibraryEvent.builder()
      .libraryEventId(null)
        .book(book)
          .build();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);

    // when
    ResponseEntity<LibraryEvent> response = testRestTemplate.exchange(PATH, HttpMethod.POST, request, LibraryEvent.class);

    // then
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    ConsumerRecord<Integer, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, TOPIC);
    // Thread.sleep(6000);
    String expected = "{\"libraryEventId\":null,\"libraryEventType\":\"NEW\",\"book\":{\"bookId\":456,\"bookName\":\"kafka Using Spring Boot\",\"bookAuthor\":\"Cristian Real\"}}";
    String value = consumerRecord.value();

    Assertions.assertEquals(expected, value);
  }

  @Test
  public void putLibraryEventTest() throws InterruptedException {
    // given
    Book book = Book.builder()
      .bookId(456)
      .bookAuthor("Cristian Real")
      .bookName("kafka Using Spring Boot")
      .build();

    LibraryEvent libraryEvent = LibraryEvent.builder()
      .libraryEventId(123)
      .book(book)
      .build();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);

    // when
    ResponseEntity<LibraryEvent> response = testRestTemplate.exchange(PATH, HttpMethod.PUT, request, LibraryEvent.class);

    // then
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    ConsumerRecord<Integer, String> consumerRecord = KafkaTestUtils.getSingleRecord(consumer, TOPIC);
    // Thread.sleep(6000);
    String expected = "{\"libraryEventId\":123,\"libraryEventType\":\"UPDATE\",\"book\":{\"bookId\":456,\"bookName\":\"kafka Using Spring Boot\",\"bookAuthor\":\"Cristian Real\"}}";
    String value = consumerRecord.value();

    Assertions.assertEquals(expected, value);
  }

}
