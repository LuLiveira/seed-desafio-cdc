package dev.lucas.desafiocdc.categories.domain;

import dev.lucas.desafiocdc.books.domain.Book;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categorie")
    private List<Book> bookId;

    private Categorie(String name) {
        this.name = name;
    }

    public Categorie(){}

    private Categorie(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBookId() {
        return bookId;
    }

    public static Categorie of(String name) {
        return new Categorie(name);
    }
    public static Categorie of(long id) {
        return new Categorie(id);
    }
}
