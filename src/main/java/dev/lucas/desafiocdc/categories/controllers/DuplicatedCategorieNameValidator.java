package dev.lucas.desafiocdc.categories.controllers;

import dev.lucas.desafiocdc.categories.controllers.repositories.CategorieRepository;
import dev.lucas.desafiocdc.categories.controllers.request.CategorieRequest;
import dev.lucas.desafiocdc.categories.domain.Categorie;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicatedCategorieNameValidator implements Validator {

    private final CategorieRepository repository;

    public DuplicatedCategorieNameValidator(CategorieRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategorieRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategorieRequest categorieRequest = (CategorieRequest) target;
        Optional<Categorie> categorie = repository.findByName(categorieRequest.name());

        if (categorie.isPresent()) {
            errors.rejectValue("name", null, "Name already exists=" + categorieRequest.name());;
        }
    }
}
