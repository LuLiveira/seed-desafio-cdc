package dev.lucas.desafiocdc.autores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lucas.desafiocdc.autores.controllers.request.AuthorRequest;
import dev.lucas.desafiocdc.autores.controllers.response.AuthorResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @PersistenceContext //Verificar o que faz essa anotação
    private EntityManager manager;

    @Transactional
    @PostMapping("/new")
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorRequest request){
        var author = request.toAuthor();
        manager.persist(author);
        var response = AuthorResponse.fromAuthor(author);

        return ResponseEntity.ok(response);
    }

}
