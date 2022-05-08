package com.example.demo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @NotEmpty(message = "Can not be empty")
    @Pattern(regexp = "^(\\d{10})?$", message = "Must contain at least 10 digits")
    @Column(name = "isbn")
    String isbn;

    @Column(name = "author")
    String author;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<UserEntity> users;

}
