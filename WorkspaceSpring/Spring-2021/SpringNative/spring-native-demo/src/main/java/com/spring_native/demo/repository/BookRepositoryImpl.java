package com.spring_native.demo.repository;

import com.spring_native.demo.models.Book;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements IBookRepository {

    List<Book> books = new ArrayList<>();

    public BookRepositoryImpl(){
        getBooks();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findById(Long id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    private void getBooks()
    {
        books.add(new Book(1, "Harry Potter", LocalDate.of(1999, 03, 12)));
        books.add(new Book(2, "100 años de soledad", LocalDate.of(1985, 12, 24)));
        books.add(new Book(3, "Aladin", LocalDate.of(1999, 03, 12)));
        books.add(new Book(4, "El arte de la guerra", LocalDate.of(1715, 01, 01)));
        books.add(new Book(5, "Cthul, dios de la destrucción", LocalDate.of(1950, 10, 17)));
    }
}
