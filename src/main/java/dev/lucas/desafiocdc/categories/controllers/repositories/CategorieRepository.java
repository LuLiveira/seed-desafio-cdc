package dev.lucas.desafiocdc.categories.controllers.repositories;

import dev.lucas.desafiocdc.categories.domain.Categorie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategorieRepository extends CrudRepository<Categorie, Long> {
    Optional<Categorie> findByName(String name);
}
