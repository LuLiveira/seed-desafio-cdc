package dev.lucas.desafiocdc.authors.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
        this.instant = Instant.now();
    }

    public static Author of (String nome, String email, String descricao) {
        return new Author(nome, email, descricao);
    }
}
