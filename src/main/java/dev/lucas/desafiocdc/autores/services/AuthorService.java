package dev.lucas.desafiocdc.autores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.lucas.desafiocdc.autores.domain.Author;
import dev.lucas.desafiocdc.autores.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;
    

    public Author create(Author author) {
        return repository.save(author);
    }
}
