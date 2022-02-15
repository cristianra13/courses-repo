package com.spring_native.demo.contorller;

import com.spring_native.demo.models.Book;
import com.spring_native.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/native/books")
public class BookController {

    @Autowired
    private IBookService service;

    @GetMapping("/")
    public ResponseEntity<?> getBooks(){
        List<Book> allBooks = service.findAll();
        if(allBooks.size() > 0)
            return  ResponseEntity.ok(allBooks);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        service.save(book);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooksbyId(@PathVariable Long id){
        Book book = service.findById(id);
        if(book != null)
            return  ResponseEntity.ok(book);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
