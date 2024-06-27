package dev.lucas.desafiocdc.books.domain;

import dev.lucas.desafiocdc.authors.domain.Author;
import dev.lucas.desafiocdc.categories.domain.Categorie;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    private String resume;

    private String summary;

    private BigDecimal price;

    private int pages;
    private LocalDate releaseDate;

    @ManyToMany
    private List<Categorie> categorie = new ArrayList<>();

    @ManyToOne
    private Author author;


    @Deprecated
    public Book() {
    }

    private Book(String title, String isbn, String resume, BigDecimal price, int pages, LocalDate releaseDate, List<Categorie> categories, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.pages = pages;
        this.releaseDate = releaseDate;
        this.categorie = categories;
        this.author = author;
        this.price = price;
        this.resume = resume;
    }

    public static Book of(String title, String isbn, String resume, @Min(20) @Positive BigDecimal price, int pages,
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

    public BigDecimal getPrice() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", resume='" + resume + '\'' +
                ", summary='" + summary + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", releaseDate=" + releaseDate +
                ", categorie=" + categorie +
                ", author=" + author +
                '}';
    }
}
