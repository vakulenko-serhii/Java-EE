package com.example.demo.service;

import com.example.demo.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;
    List<BookEntity> books = new ArrayList<>();

    @Transactional // Describes a transaction attribute on an individual method or on a class.
    public BookEntity add(BookEntity book) {
        return entityManager.merge(book);
    }

    public List<BookEntity> getAll() {
        return entityManager.createQuery("SELECT c FROM BookEntity c", BookEntity.class)
                .getResultList();
    }

    public List<BookEntity> findByNameIsbn(String name, String isbn, String author) {
        return entityManager.createQuery(
                        "SELECT c FROM BookEntity c WHERE " +
                                "(c.isbn = :isbn) OR " +
                                "(c.name = :name) OR " +
                                "(c.author = :author)",
                        BookEntity.class)
                .setParameter("isbn", isbn)
                .setParameter("name", name)
                .setParameter("author", author)
                .getResultList();
    }
    public BookEntity findByIsbn(String isbn) {
        return entityManager.createQuery(
                        "SELECT c FROM BookEntity c WHERE c.isbn = :isbn", BookEntity.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }
}
