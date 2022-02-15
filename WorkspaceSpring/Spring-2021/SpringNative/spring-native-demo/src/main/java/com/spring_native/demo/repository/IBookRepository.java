package com.spring_native.demo.repository;

import com.spring_native.demo.models.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
}
