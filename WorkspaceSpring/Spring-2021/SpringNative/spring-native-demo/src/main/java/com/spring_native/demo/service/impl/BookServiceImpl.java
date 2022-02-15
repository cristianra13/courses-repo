package com.spring_native.demo.service.impl;

import com.spring_native.demo.models.Book;
import com.spring_native.demo.repository.IBookRepository;
import com.spring_native.demo.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    private IBookRepository repository;

    public BookServiceImpl(IBookRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Book book) {
        Optional<Book> optBook = repository.findAll().stream().filter(b -> b.getId() == book.getId()).findFirst();
        if(optBook.isPresent()){
            optBook.get().setName(book.getName());
            optBook.get().setYear(book.getYear());
        }
        else {
            repository.save(book);
        }
    }
}
