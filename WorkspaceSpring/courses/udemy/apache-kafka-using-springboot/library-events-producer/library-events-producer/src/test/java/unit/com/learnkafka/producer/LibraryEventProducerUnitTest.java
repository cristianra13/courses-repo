package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {

  private final String TOPIC = "library-events";

  @Mock
  private KafkaTemplate<Integer, String> kafkaTemplate;

  @Spy
  private ObjectMapper mapper = new ObjectMapper();

  @InjectMocks
  private LibraryEventProducer libraryEventProducer;

  @Test
  void sendLibraryEventApproach2Fail() throws JsonProcessingException, ExecutionException, InterruptedException {
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

    SettableListenableFuture future = new SettableListenableFuture();
    future.setException(new RuntimeException("Exception calling kafka"));

    when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

    // when
    assertThrows(Exception.class, () -> libraryEventProducer.sendLibraryEventApproach2(libraryEvent).get());

    // then

  }

  @Test
  void sendLibraryEventApproach2Success() throws JsonProcessingException, ExecutionException, InterruptedException {
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

    String jsonLibraryEvent = mapper.writeValueAsString(libraryEvent);
    SettableListenableFuture future = new SettableListenableFuture();
    ProducerRecord<Integer, String> producerRecord =
      new ProducerRecord(TOPIC, libraryEvent.getLibraryEventId(), jsonLibraryEvent);

    RecordMetadata recordMetadata =
      new RecordMetadata(new TopicPartition(TOPIC, 1), 1, 1, System.currentTimeMillis(), 1, 2);

    SendResult<Integer, String> sendResult = new SendResult<>(producerRecord, recordMetadata);
    future.set(sendResult);

    when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

    // when
    ListenableFuture<SendResult<Integer, String>> listenableFuture = libraryEventProducer.sendLibraryEventApproach2(libraryEvent);

    // then
    SendResult<Integer, String> sendResultListenable = listenableFuture.get();
    assert sendResultListenable.getRecordMetadata().partition() == 1;

  }

}
