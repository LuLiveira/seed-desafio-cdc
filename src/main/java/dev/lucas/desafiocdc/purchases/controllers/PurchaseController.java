package dev.lucas.desafiocdc.purchases.controllers;

import dev.lucas.desafiocdc.purchases.controllers.requests.PersonRequest;
import dev.lucas.desafiocdc.purchases.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping("/new")
    public void post(@RequestBody @Valid PersonRequest personRequest){
        Person person = personRequest.toPerson(manager);
    }
}
