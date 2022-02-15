package com.spring_native.demo.service;

import com.spring_native.demo.models.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
}
