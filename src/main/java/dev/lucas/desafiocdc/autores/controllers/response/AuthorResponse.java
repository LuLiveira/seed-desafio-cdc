package dev.lucas.desafiocdc.autores.controllers.response;

import java.time.Instant;

import dev.lucas.desafiocdc.autores.domain.Author;

public record AuthorResponse(String name, String email, String description, Instant instant) {

    public static AuthorResponse fromAuthor(Author novoAutor) {
        return new AuthorResponse(novoAutor.getName(), 
        novoAutor.getEmail(),
        novoAutor.getDescription(), 
        novoAutor.getInstant());
    }
    
}
