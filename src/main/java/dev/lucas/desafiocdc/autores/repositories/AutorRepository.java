package dev.lucas.desafiocdc.autores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.lucas.desafiocdc.autores.domain.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {  
}
