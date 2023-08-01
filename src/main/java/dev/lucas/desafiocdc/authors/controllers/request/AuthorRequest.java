package dev.lucas.desafiocdc.authors.controllers.request;

import dev.lucas.desafiocdc.authors.domain.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotBlank(message = "Name can not be blank") String name,

        @NotBlank(message = "E-mail can not be blank") @Email(message = "E-mail should be valid") String email,

        @NotBlank(message = "Description can not be blank") @Size(max = 400, message = "Description size must be between 1 and 400") String description) {

    public Author toAuthor() {
        return Author.of(name, email, description);
    }
}
