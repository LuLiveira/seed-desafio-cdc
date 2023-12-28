package dev.lucas.desafiocdc.books.domain;

import dev.lucas.desafiocdc.authors.domain.Author;
import dev.lucas.desafiocdc.categories.domain.Categorie;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    private String resume;

    private String summary;

    private double price;

    private int pages;
    private LocalDate releaseDate;

    @ManyToMany
    private List<Categorie> categorie = new ArrayList<>();

    @ManyToOne
    private Author author;


    @Deprecated
    public Book() {
    }

    private Book(String title, String isbn, String resume, double price, int pages, LocalDate releaseDate, List<Categorie> categories, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.pages = pages;
        this.releaseDate = releaseDate;
        this.categorie = categories;
        this.author = author;
        this.price = price;
        this.resume = resume;
    }

    public static Book of(String title, String isbn, String resume, double price, int pages,
                          LocalDate releaseDate, List<Categorie> categories, Author author) {
        return new Book(title, isbn, resume,
                price, pages, releaseDate, categories, author);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<Categorie> getCategorie() {
        return categorie;
    }

    public Author getAuthor() {
        return author;
    }
}
