package com.example.demo.service;

import com.example.demo.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByNameAndIsbnAndAuthor(String isbn, String name, String author);

}
