package dev.lucas.desafiocdc.states.controllers;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/states")
@RestController
public class StateController {


    @PersistenceContext
    private final EntityManager entityManager;

    public StateController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping("/new")
    public void teste() {

        entityManager.persist(new State("São Paulo", new Country(1l)));

    }
}
