package dev.lucas.desafiocdc.books.controllers.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lucas.desafiocdc.authors.controllers.response.AuthorResponse;
import dev.lucas.desafiocdc.books.domain.Book;
import dev.lucas.desafiocdc.categories.controllers.response.CategorieResponse;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record BookFormResponse(Long id,
                               String title,
                               String isbn,
                               String resume,
                               String summary,
                               double price,
                               int pages,
                               @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy")
                               LocalDate releaseDate,

                               @JsonProperty("categories")
                               List<CategorieResponse> categorieResponses,

                               @JsonProperty("author")
                               AuthorResponse authorResponse) {
    public static BookFormResponse from(Book book) {
        return new BookFormResponse(book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getResume(),
                book.getSummary(),
                book.getPrice(),
                book.getPages(),
                book.getReleaseDate(),
                book.getCategorie().stream().map(CategorieResponse::fromCategorie).toList(),
                AuthorResponse.fromAuthor(book.getAuthor()));
    }
}
