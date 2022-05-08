package com.example.demo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class BookEntity {
    @Column(name = "name")
    String name;

    @Id
    @Column(name = "isbn")
    String isbn;

    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<UserEntity> users;

}
