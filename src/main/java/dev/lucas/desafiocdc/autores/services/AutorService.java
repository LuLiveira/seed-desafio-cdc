package dev.lucas.desafiocdc.autores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.lucas.desafiocdc.autores.domain.Autor;
import dev.lucas.desafiocdc.autores.repositories.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;
    

    public Autor novoAutor(Autor autor) {
        return repository.save(autor);
    }
}
