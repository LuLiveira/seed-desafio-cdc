package dev.lucas.desafiocdc.states.controllers;

import dev.lucas.desafiocdc.states.controllers.request.StateForm;
import dev.lucas.desafiocdc.states.controllers.response.StateResponse;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<StateResponse> post(@Valid @RequestBody StateForm form) {
        State state = form.toState(entityManager);
        entityManager.persist(state);
        var stateResponse = StateResponse.from(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(stateResponse);
    }
}
