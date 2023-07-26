package dev.lucas.desafiocdc.autores.controllers.request;

import dev.lucas.desafiocdc.autores.domain.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorRequest(@NotNull @NotBlank String name,

@NotNull (message = "E-mail can not be null")
    @NotBlank (message = "E-mail can not be empty or blank")
    @Email(message = "E-mail should be valid")
    String email,

@NotNull(message = "Description can not be null")
    @Size(max = 400, message = "Description size must be between 1 and 400")
    String description)
{

    public Author toAuthor() {
        return Author.of(name, email, description);
    }
}
