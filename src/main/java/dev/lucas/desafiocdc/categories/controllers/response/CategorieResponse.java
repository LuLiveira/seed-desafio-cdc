package dev.lucas.desafiocdc.categories.controllers.response;

import dev.lucas.desafiocdc.categories.domain.Categorie;

public record CategorieResponse (Long id, String name) {

    public static CategorieResponse fromCategorie(Categorie categorie) {
        return new CategorieResponse(categorie.getId(), categorie.getName());
    }
}
