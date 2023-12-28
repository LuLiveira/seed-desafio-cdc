package dev.lucas.desafiocdc.categories.controllers.response;

import dev.lucas.desafiocdc.categories.domain.Categorie;

import java.util.List;

public record CategorieResponse (Long id, String name) {

    public static CategorieResponse fromCategorie(Categorie categorie) {
        return new CategorieResponse(categorie.getId(), categorie.getName());
    }

    public static List<CategorieResponse> fromCategorie(List<Categorie> categorie) {
        return categorie.stream().map(CategorieResponse::fromCategorie).toList();
    }
}
