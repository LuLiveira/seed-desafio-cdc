package dev.lucas.desafiocdc.books.controllers.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import dev.lucas.desafiocdc.authors.controllers.AuthorRepository;
import dev.lucas.desafiocdc.authors.domain.Author;
import dev.lucas.desafiocdc.books.domain.Book;
import dev.lucas.desafiocdc.categories.controllers.repositories.CategorieRepository;
import dev.lucas.desafiocdc.categories.domain.Categorie;
import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record BookForm(
        @NotNull @NotEmpty @UniqueValue(domainClass = Book.class, fieldName = "title") String title,
        @NotNull @NotEmpty @Length(max = 500)
        String resume,
        String summary,
        @Min(20) @Positive
        double price,
        @Min(100) @Positive
        int pages,
        @NotNull @NotEmpty @UniqueValue(domainClass = Book.class, fieldName = "isbn") @Positive
        String isbn,
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
        LocalDate releaseDate,

        //TODO criar uma validacao de inteiros para lista
        List<Long> caterogieId,
        @Positive
        int authorId
) {

    public Book toBook(AuthorRepository authorRepository, CategorieRepository categorieRepository) {
        var categories = caterogieId.stream().map(categorieRepository::findById).map(
                Optional::orElseThrow //TODO deve lançar uma expcetion de CategorieNotFoundException
        ).toList();

        var author = authorRepository.findById((long) authorId).orElseThrow();
        //TODO deve lançar uma expcetion de AuthorNotFoundException

        var book = Book.of(this.title,
                this.isbn,
                this.resume,
                this.price,
                this.pages,
                this.releaseDate,
                categories,
                author);

        if (summary == null) return book;
        book.setSummary(summary);
        return book;
    }
}
