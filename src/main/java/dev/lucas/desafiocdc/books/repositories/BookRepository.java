package dev.lucas.desafiocdc.books.repositories;

import dev.lucas.desafiocdc.books.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
