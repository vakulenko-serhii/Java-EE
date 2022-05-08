package com.example.demo.controller;

import com.example.demo.model.BookEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.model.security.AuthenticatedUser;
import com.example.demo.service.BookRepository;
import com.example.demo.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    //private final BookService bookService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<BookEntity>> saveBook(
            @RequestBody final BookEntity book
    ) {
        bookRepository.save(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<List<BookEntity>> findBooks(
            @RequestBody final BookEntity book
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findByNameAndIsbnAndAuthor(book.getName(),book.getIsbn(),book.getAuthor()));
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookRepository.findAll());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public ResponseEntity<List<BookEntity>> getFavorite() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();

        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        for (BookEntity b :userEntity.getBooks()) {
            System.out.println(b);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userEntity.getBooks());
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/addtofavourite", method = RequestMethod.POST)
    public void addToFavorite(@RequestBody final BookEntity book) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
        BookEntity bookEntity = bookRepository.findById(book.getIsbn()).get();
        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        userEntity.getBooks().add(bookEntity);

        userRepository.save(userEntity);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/deletefromfavourite", method = RequestMethod.POST)
    public void deleteFromFavorite(@RequestBody final BookEntity book) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final AuthenticatedUser authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
        BookEntity bookEntity = bookRepository.findById(book.getIsbn()).get();
        UserEntity userEntity = userRepository.findByLogin(authenticatedUser.getUsername()).get();
        userEntity.getBooks().remove(bookEntity);

        userRepository.save(userEntity);
    }

}