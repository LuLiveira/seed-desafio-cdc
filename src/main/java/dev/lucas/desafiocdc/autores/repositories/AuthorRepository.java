package dev.lucas.desafiocdc.autores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.lucas.desafiocdc.autores.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {  
}
