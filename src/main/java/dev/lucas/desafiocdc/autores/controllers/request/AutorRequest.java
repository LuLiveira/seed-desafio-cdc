package dev.lucas.desafiocdc.autores.controllers.request;

import dev.lucas.desafiocdc.autores.domain.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AutorRequest (
    @NotNull @NotBlank
    String nome, 

    @NotNull @NotBlank @Email
    String email, 
    @NotNull @Size(max = 400, message = "Descrição deve conter no máximo 400 caracteres")
    String descricao) {


    public Autor toAutor() {
        return Autor.of(nome, email, descricao);
    }
}
