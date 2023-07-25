package dev.lucas.desafiocdc.autores.controllers.response;

import java.time.Instant;

import dev.lucas.desafiocdc.autores.domain.Autor;

public record AutorResponse(String nome, String email, String descricao, Instant momento) {

    public static AutorResponse fromAutor(Autor novoAutor) {
        return new AutorResponse(novoAutor.getNome(), 
        novoAutor.getEmail(),
        novoAutor.getDescricao(), 
        novoAutor.getMomento());
    }
    
}
