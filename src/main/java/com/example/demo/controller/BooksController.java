package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    //private final BookService bookService;
    private final BookRepository bookRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> saveBook(
            @RequestBody final Book book
    ) {
        bookRepository.save(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> findBooks(
            @RequestBody final Book book
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findByNameAndIsbnAndAuthor(book.getName(),book.getIsbn(),book.getAuthor()));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

}