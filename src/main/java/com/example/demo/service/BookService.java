package com.example.demo.service;

import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;
    List<Book> books = new ArrayList<>();

    @Transactional // Describes a transaction attribute on an individual method or on a class.
    public Book add(Book book) {
        return entityManager.merge(book);
    }

    public List<Book> getAll() {
        return entityManager.createQuery("SELECT c FROM Book c",Book.class)
                .getResultList();
    }

    public List<Book> findByNameIsbn(String name, String isbn, String author) {
        return entityManager.createQuery(
                        "SELECT c FROM Book c WHERE " +
                                "(c.isbn = :isbn) OR " +
                                "(c.name = :name) OR " +
                                "(c.author = :author)",
                        Book.class)
                .setParameter("isbn", isbn)
                .setParameter("name", name)
                .setParameter("author", author)
                .getResultList();
    }
    public Book findByIsbn(String isbn) {
        return entityManager.createQuery(
                        "SELECT c FROM Book c WHERE c.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }
}
