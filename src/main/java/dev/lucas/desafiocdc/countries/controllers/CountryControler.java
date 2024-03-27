package dev.lucas.desafiocdc.countries.controllers;

import dev.lucas.desafiocdc.countries.controllers.request.CountryRequest;
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

@RestController
@RequestMapping("/countries")
public class CountryControler {


    @PersistenceContext
    private final EntityManager manager;

    public CountryControler(EntityManager manager) {
        this.manager = manager;
    }


    @Transactional
    @PostMapping("/new")
    public ResponseEntity<CountryRequest> post(@Valid @RequestBody CountryRequest countryRequest) {
        var country = countryRequest.toCoutry();
        manager.persist(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryRequest);
    }
}
