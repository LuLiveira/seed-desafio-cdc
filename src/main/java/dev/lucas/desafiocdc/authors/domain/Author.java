package dev.lucas.desafiocdc.authors.domain;

import java.time.Instant;
import java.util.List;

import dev.lucas.desafiocdc.books.domain.Book;
import jakarta.persistence.*;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Embedded
    private String name;

    // @Embedded
    private String email;

    private String description;

    private Instant instant;

    @OneToMany(mappedBy = "author")
    private List<Book> bookId;

    private Author(long authorId) {
        this.id = authorId;
    }

    public static Author of(long authorId) {
        return new Author(authorId);
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public Author(){}

    private Author(String name, String email, String description){
        this.name = name;
        this.email = email;
        this.description = description;
        this.instant = Instant.now(); //TODO colocar UTF-3
    }

    public static Author of (String nome, String email, String descricao) {
        return new Author(nome, email, descricao);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
