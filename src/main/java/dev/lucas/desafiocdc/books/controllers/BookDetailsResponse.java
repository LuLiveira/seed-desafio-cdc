package dev.lucas.desafiocdc.books.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lucas.desafiocdc.authors.controllers.response.AuthorResponse;
import dev.lucas.desafiocdc.books.controllers.response.BookResponse;
import dev.lucas.desafiocdc.books.domain.Book;
import dev.lucas.desafiocdc.categories.controllers.response.CategorieResponse;
import dev.lucas.desafiocdc.categories.domain.Categorie;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record BookDetailsResponse (

        @JsonProperty("author")
        AuthorResponse authorResponse,

        @JsonProperty("categories")
        List<CategorieResponse> categorieResponse,

        @JsonProperty("book")
        BookResponse bookResponse,

        String isbn,
        String resume,
        String summary,
        double price,
        int pages,

        @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate) {

    public static BookDetailsResponse fromBook(Book book){
        return new BookDetailsResponse(
                AuthorResponse.fromAuthor(book.getAuthor()),
                CategorieResponse.fromCategorie(book.getCategorie()),
                BookResponse.fromBook(book),
                book.getIsbn(),
                book.getResume(),
                book.getSummary(),
                book.getPrice(),
                book.getPages(),
                book.getReleaseDate()
        );
    }
}
