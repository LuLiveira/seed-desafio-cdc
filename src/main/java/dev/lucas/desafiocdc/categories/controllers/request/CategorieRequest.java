package dev.lucas.desafiocdc.categories.controllers.request;

import dev.lucas.desafiocdc.categories.domain.Categorie;
import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategorieRequest (@UniqueValue (fieldName = "name", domainClass = Categorie.class) @NotNull @NotEmpty String name){

    public Categorie toCategorie() {
        return Categorie.of(this.name);
    }
}

