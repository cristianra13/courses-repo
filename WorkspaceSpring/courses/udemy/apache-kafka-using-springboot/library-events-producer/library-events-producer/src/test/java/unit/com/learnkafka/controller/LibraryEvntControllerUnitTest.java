package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.domain.Book;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEvntControllerUnitTest {

  private final String PATH = "/v1/libraryevent";
  private final String TOPIC = "library-events";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private LibraryEventProducer libraryEventProducer;

  @Test
  void postLibraryEventsControllerTest() throws Exception {
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

    String jsonRequest = mapper.writeValueAsString(libraryEvent);
    when(libraryEventProducer.sendLibraryEventApproach2(isA(LibraryEvent.class))).thenReturn(null);

    // when
    mockMvc.perform(post(PATH)
        .content(jsonRequest)
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isCreated());

    // then
  }

  @Test
  void postLibraryEventsControllerTest_4xx() throws Exception {
    // given
    Book book = Book.builder()
      .bookId(null)
      .bookAuthor(null)
      .bookName("kafka Using Spring Boot")
      .build();

    LibraryEvent libraryEvent = LibraryEvent.builder()
      .libraryEventId(null)
      .book(book)
      .build();

    String jsonRequest = mapper.writeValueAsString(libraryEvent);
    when(libraryEventProducer.sendLibraryEventApproach2(isA(LibraryEvent.class))).thenReturn(null);

    // when
    String expectedError = "book.bookAuthor - must not be blank,book.bookId - must not be null";
    mockMvc.perform(post(PATH)
        .content(jsonRequest)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().is4xxClientError())
      .andExpect(content().string(expectedError));

    // then
  }

  @Test
  void putLibraryEventsControllerTest() throws Exception {
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

    String jsonRequest = mapper.writeValueAsString(libraryEvent);
    when(libraryEventProducer.sendLibraryEventApproach2(isA(LibraryEvent.class))).thenReturn(null);

    // when
    mockMvc.perform(put(PATH)
        .content(jsonRequest)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk());

    // then
  }

  @Test
  void pustLibraryEventsControllerTest_4xx() throws Exception {
    // given
    Book book = Book.builder()
      .bookId(null)
      .bookAuthor(null)
      .bookName("kafka Using Spring Boot")
      .build();

    LibraryEvent libraryEvent = LibraryEvent.builder()
      .libraryEventId(null)
      .book(book)
      .build();

    String jsonRequest = mapper.writeValueAsString(libraryEvent);
    when(libraryEventProducer.sendLibraryEventApproach2(isA(LibraryEvent.class))).thenReturn(null);

    // when
    String expectedError = "book.bookAuthor - must not be blank,book.bookId - must not be null";
    mockMvc.perform(put(PATH)
        .content(jsonRequest)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().is4xxClientError())
      .andExpect(content().string(expectedError));

    // then
  }

}
