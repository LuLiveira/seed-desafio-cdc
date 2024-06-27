package dev.lucas.desafiocdc.books.controllers;

import dev.lucas.desafiocdc.authors.controllers.AuthorRepository;
import dev.lucas.desafiocdc.books.controllers.form.BookForm;
import dev.lucas.desafiocdc.books.controllers.response.BookDetailsResponse;
import dev.lucas.desafiocdc.books.controllers.response.BookFormResponse;
import dev.lucas.desafiocdc.books.controllers.response.BookResponse;
import dev.lucas.desafiocdc.books.domain.Book;
import dev.lucas.desafiocdc.books.repositories.BookRepository;
import dev.lucas.desafiocdc.categories.repositories.CategorieRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {


    //TODO voltar a usar o EntityManager para manter o Controller coeso
    private final BookRepository repository;
    private final CategorieRepository categorieRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository repository, CategorieRepository categorieRepository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.categorieRepository = categorieRepository;
        this.authorRepository = authorRepository;
    }


    @PostMapping("/new")
    @Transactional
    public ResponseEntity<BookFormResponse> post(@Valid @RequestBody BookForm request) {
        var book = request.toBook(authorRepository, categorieRepository);
        repository.save(book);
        BookFormResponse from = BookFormResponse.from(book);

        return ResponseEntity.ok(from);
    }

    @GetMapping // TODO criar um cache e utilizar o method HEAD para validar a existencia/validade do cache
    public ResponseEntity<List<BookResponse>> get() {
        var books = repository.findAll();

        var booksResponse = books.stream().map(BookResponse::fromBook).toList();

        return ResponseEntity.ok(booksResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Book> book = repository.findById(id);

        return book.isPresent() ? ResponseEntity.ok(BookDetailsResponse.fromBook(book.get())):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Livro não encontrado"));
    }
}
