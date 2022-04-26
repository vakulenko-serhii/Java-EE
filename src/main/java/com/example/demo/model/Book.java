package com.example.demo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class Book {
    @Column(name = "name")
    String name;

    @Id
    @Column(name = "isbn")
    String isbn;

    @Column(name = "author")
    String author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return isbn != null && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
