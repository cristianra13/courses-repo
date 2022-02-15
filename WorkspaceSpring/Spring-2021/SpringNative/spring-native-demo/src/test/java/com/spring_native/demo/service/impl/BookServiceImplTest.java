package com.spring_native.demo.service.impl;

import com.spring_native.demo.models.Book;
import com.spring_native.demo.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private IBookService service;

    @Test
    public void getAllBooksTest(){
        List<Book> books = service.findAll();
        assertTrue(books.size() > 0);
    }

    @Test
    public void save(){
        Book book = new Book(6, "La quinta ola", LocalDate.of(2010, 07, 28));
        service.save(book);
        assertEquals(book, service.findAll().stream().filter(b -> b.getId() == book.getId()).findFirst().orElse(null));
    }
}