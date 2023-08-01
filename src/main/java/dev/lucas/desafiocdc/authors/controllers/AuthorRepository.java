package dev.lucas.desafiocdc.authors.controllers;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.lucas.desafiocdc.authors.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

    Optional<Author> findByEmail(String email);

}
