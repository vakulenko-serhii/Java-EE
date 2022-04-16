package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> saveBook(
            @RequestBody final Book book
    ) {
        bookService.add(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAll());
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> findBooks(
            @RequestBody final Book book
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findByNameIsbn(book.getName(),book.getIsbn()));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAll());
    }
}