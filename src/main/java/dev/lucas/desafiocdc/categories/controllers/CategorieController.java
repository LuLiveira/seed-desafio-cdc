package dev.lucas.desafiocdc.categories.controllers;

import dev.lucas.desafiocdc.categories.controllers.request.CategorieRequest;
import dev.lucas.desafiocdc.categories.controllers.response.CategorieResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    @PersistenceContext
    private EntityManager manager;
    private final DuplicatedCategorieNameValidator nameValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(nameValidator);
    }

    public CategorieController(DuplicatedCategorieNameValidator nameValidator, EntityManager manager) {
        this.nameValidator = nameValidator;
        this.manager = manager;
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<CategorieResponse> post(@Valid @RequestBody CategorieRequest request) {
        var categorie = request.toCategorie();
        manager.persist(categorie);
        var categorieResponse = CategorieResponse.fromCategorie(categorie);

        return ResponseEntity.ok(categorieResponse);
    }
}
