package dev.lucas.desafiocdc.categories.controllers.request;

import dev.lucas.desafiocdc.categories.domain.Categorie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorieRequest (@NotNull @NotEmpty String name){

    public Categorie toCategorie() {
        return Categorie.of(this.name);
    }
}

