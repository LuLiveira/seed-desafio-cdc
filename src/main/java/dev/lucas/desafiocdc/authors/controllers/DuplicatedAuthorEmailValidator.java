package dev.lucas.desafiocdc.authors.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dev.lucas.desafiocdc.authors.controllers.request.AuthorRequest;
import dev.lucas.desafiocdc.authors.domain.Author;

@Component
@Deprecated
public class DuplicatedAuthorEmailValidator implements Validator {

    @Autowired
    private AuthorRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //Spring go apply his validation before custom validation then if it find any errors in his validation do not need run the custom validation
        if (errors.hasErrors()) {
            return;
        }
         
        AuthorRequest authorRequest = (AuthorRequest) target;
        Optional<Author> author = repository.findByEmail(authorRequest.email());

        if (author.isPresent()) {
            errors.rejectValue("email", null, "E-mail already exists " + authorRequest.email());;
        }

    }

}
