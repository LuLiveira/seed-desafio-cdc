package dev.lucas.desafiocdc.authors.controllers.response;

import java.time.Instant;

import dev.lucas.desafiocdc.authors.domain.Author;

public record AuthorResponse(String name, String email, String description, Instant instant) {

    public static AuthorResponse fromAuthor(Author novoAutor) {
        return new AuthorResponse(novoAutor.getName(), 
        novoAutor.getEmail(),
        novoAutor.getDescription(), 
        novoAutor.getInstant());
    }
    
}
