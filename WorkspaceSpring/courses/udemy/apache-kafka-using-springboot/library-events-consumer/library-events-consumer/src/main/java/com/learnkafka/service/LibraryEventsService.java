package com.learnkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.entity.LibraryEvent;
import com.learnkafka.jpa.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventsService {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private LibraryEventsRepository libraryEventsRepository;

  public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
    LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
    log.info("LibraryEvent: {}", libraryEvent);

    switch (libraryEvent.getLibraryEventType()) {
      case NEW:
        // save operation
        save(libraryEvent);
        break;
      case UPDATE:
        // validate libraryEventId
        validateLibraryEvent(libraryEvent);
        // save operation
        save(libraryEvent);
        break;
        default:
          log.info("Unknown event type: {}", libraryEvent.getLibraryEventType());

  }

}

  private void validateLibraryEvent(LibraryEvent libraryEvent) {
    if (libraryEvent.getLibraryEventId() == null) {
      throw new IllegalArgumentException("LibraryEventId is missing");
    }
    Optional<LibraryEvent> libraryEventOptional = libraryEventsRepository.findById(libraryEvent.getLibraryEventId());
    if (libraryEventOptional.isEmpty()) {
      throw new  IllegalArgumentException("LibraryEventId not found");
    }
    log.info("validation is successful for LibraryEventId: {}", libraryEventOptional.get());
  }

  private void save(LibraryEvent libraryEvent) {
    // save operation
    libraryEvent.getBook().setLibraryEvent(libraryEvent);
    libraryEventsRepository.save(libraryEvent);
    log.info("LibraryEvent saved: {}", libraryEvent);
  }

}
