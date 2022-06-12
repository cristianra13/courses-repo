package com.learnkafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.entity.Book;
import com.learnkafka.entity.LibraryEvent;
import com.learnkafka.entity.LibraryEventType;
import com.learnkafka.jpa.LibraryEventsRepository;
import com.learnkafka.service.LibraryEventsService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.isA;

@SpringBootTest
@EmbeddedKafka(topics = {"library-events"}, partitions = 3)
// Override kafka configurations
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}"
  , "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
public class LibraryEventsConsumerIntegrationTest {

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  private KafkaTemplate<Integer, String> kafkaTemplate;

  @Autowired
  private KafkaListenerEndpointRegistry endpointRegistry;

  @SpyBean
  private LibraryEventsConsumer libraryEventsConsumerSpy;

  @SpyBean
  private LibraryEventsService libraryEventsServiceSpy;

  @Autowired
  private LibraryEventsRepository libraryEventsRepositor;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    for (MessageListenerContainer messageListenerContainer : endpointRegistry.getListenerContainers()) {
      ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
    }
  }

  @AfterEach
  void tearDown() {
    libraryEventsRepositor.deleteAll();
  }

  @Test
  void publishNewLibraryEvent() throws ExecutionException, InterruptedException, JsonProcessingException {
    String json = "{\"libraryEventId\": null, \"libraryEventType\":\"NEW\", \"book\": {\"bookId\": \"456\", \"bookName\": \"Kafka using Spring Boot\", \"bookAuthor\": \"CristianReal\"}}";
    kafkaTemplate.sendDefault(json).get();

    // when
    CountDownLatch latch = new CountDownLatch(1);
    latch.await(3, TimeUnit.SECONDS);

    // then
    Mockito.verify(libraryEventsConsumerSpy, Mockito.times(1)).onMessage(isA(ConsumerRecord.class));
    Mockito.verify(libraryEventsServiceSpy, Mockito.times(1)).processLibraryEvent(isA(ConsumerRecord.class));

    List<LibraryEvent> libraryEventList = (List<LibraryEvent>) libraryEventsRepositor.findAll();
    assert libraryEventList.size() == 1;
    libraryEventList.forEach(libraryEvent -> {
      assert libraryEvent.getLibraryEventId() != null;
      Assertions.assertEquals(456, libraryEvent.getBook().getBookId());
    });

  }

  @Test
  void publishUpdateLibraryEvent() throws ExecutionException, InterruptedException, JsonProcessingException {
    // given
    String json = "{\"libraryEventId\": null, \"libraryEventType\":\"NEW\", \"book\": {\"bookId\": \"456\", \"bookName\": \"Kafka using Spring Boot\", \"bookAuthor\": \"CristianReal\"}}";
    LibraryEvent libraryEvent = objectMapper.readValue(json, LibraryEvent.class);
    libraryEvent.getBook().setLibraryEvent(libraryEvent);
    libraryEventsRepositor.save(libraryEvent);

    // publish the update library event
    Book book = Book.builder()
      .bookId(456)
      .bookName("Kafka Using Spring Boot 2.x")
      .bookAuthor("CristianReal")
      .build();
    libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
    libraryEvent.setBook(book);
    String updatedJson = objectMapper.writeValueAsString(libraryEvent);
    kafkaTemplate.sendDefault(libraryEvent.getLibraryEventId(), updatedJson).get();

    // when
    CountDownLatch latch = new CountDownLatch(1);
    latch.await(3, TimeUnit.SECONDS);


    // then
    Mockito.verify(libraryEventsConsumerSpy, Mockito.times(1)).onMessage(isA(ConsumerRecord.class));
    Mockito.verify(libraryEventsServiceSpy, Mockito.times(1)).processLibraryEvent(isA(ConsumerRecord.class));
    LibraryEvent libraryEventPersisted = libraryEventsRepositor.findById(libraryEvent.getLibraryEventId()).get();

    Assertions.assertEquals("Kafka Using Spring Boot 2.x", libraryEventPersisted.getBook().getBookName());

  }

  @Test
  void publishUpdateLibraryEvent_null_libraryevent() throws ExecutionException, InterruptedException, JsonProcessingException {
    // given
    String json = "{\"libraryEventId\": null, \"libraryEventType\":\"UPDATE\", \"book\": {\"bookId\": \"456\", \"bookName\": \"Kafka using Spring Boot\", \"bookAuthor\": \"CristianReal\"}}";

    kafkaTemplate.sendDefault(json).get();

    // when
    CountDownLatch latch = new CountDownLatch(1);
    latch.await(5, TimeUnit.SECONDS);

    // then
    Mockito.verify(libraryEventsConsumerSpy, Mockito.times(10)).onMessage(isA(ConsumerRecord.class));
    Mockito.verify(libraryEventsServiceSpy, Mockito.times(10)).processLibraryEvent(isA(ConsumerRecord.class));

  }


}
