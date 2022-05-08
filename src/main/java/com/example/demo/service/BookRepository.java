package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByNameAndIsbnAndAuthor(String isbn, String name, String author);

}
