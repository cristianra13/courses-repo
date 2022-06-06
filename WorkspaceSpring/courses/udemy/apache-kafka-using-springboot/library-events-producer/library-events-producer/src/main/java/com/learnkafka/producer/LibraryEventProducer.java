package com.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class LibraryEventProducer {

  private final String topic = "library-events";

  @Autowired
  KafkaTemplate<Integer, String> kafkaTemplate;

  @Autowired
  private ObjectMapper mapper;

  public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
    Integer key = libraryEvent.getLibraryEventId();
    String value = mapper.writeValueAsString(libraryEvent);
    ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.sendDefault(key, value);
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
      @Override
      public void onFailure(Throwable ex) {
        handleFailure(key, value, ex);
      }

      @Override
      public void onSuccess(SendResult<Integer, String> result) {
        handlerSuccess(key, value, result);
      }
    });
  }

  public void sendLibraryEventApproach2(LibraryEvent libraryEvent) throws JsonProcessingException {
    Integer key = libraryEvent.getLibraryEventId();
    String value = mapper.writeValueAsString(libraryEvent);
    ProducerRecord<Integer, String> producerRecord = buildproducerRecord(key, value, topic);

    //ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(topic, key, value);
    ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
      @Override
      public void onFailure(Throwable ex) {
        handleFailure(key, value, ex);
      }

      @Override
      public void onSuccess(SendResult<Integer, String> result) {
        handlerSuccess(key, value, result);
      }
    });
  }

  private ProducerRecord<Integer, String> buildproducerRecord(Integer key, String value, String topic) {
    List<RecordHeader> headers = List.of(new RecordHeader("event-source", "scanner".getBytes()));
    return new ProducerRecord(topic, null, key, value, headers);
  }

  public SendResult<Integer, String> sendLibraryEventSynchronous(LibraryEvent libraryEvent)
    throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
    Integer key = libraryEvent.getLibraryEventId();
    String value = mapper.writeValueAsString(libraryEvent);
    SendResult<Integer, String> sendResult;

    try {
      sendResult = kafkaTemplate.sendDefault(key, value).get(1, TimeUnit.SECONDS);
    } catch (ExecutionException |InterruptedException e) {
      log.error("ExecutionException/InterruptedException sending message and the exception is {}", e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error("Exception sending message and the exception is {}", e.getMessage());
      throw e;
    }

    return sendResult;

  }

  private void handleFailure(Integer key, String value, Throwable ex) {
    log.error("Error sending message and the exception is {}", ex.getMessage());
    try {
      throw ex;
    } catch (Throwable e) {
      log.error("Error in onFailure: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public void handlerSuccess(Integer key,  String value, SendResult<Integer, String> result) {
    log.info("Message sent successfully for the key: {} and value: {}, and partition is {}",
      key, value, result.getRecordMetadata().partition());
  }

}
