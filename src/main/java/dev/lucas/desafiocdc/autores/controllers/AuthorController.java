package dev.lucas.desafiocdc.autores.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lucas.desafiocdc.autores.controllers.request.AuthorRequest;
import dev.lucas.desafiocdc.autores.controllers.response.AuthorResponse;
import dev.lucas.desafiocdc.autores.services.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping("/new")
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorRequest request){
        var author = request.toAuthor();
        var newAuthor = service.create(author);
        var response = AuthorResponse.fromAuthor(newAuthor);

        return ResponseEntity.ok(response);
    }

}
