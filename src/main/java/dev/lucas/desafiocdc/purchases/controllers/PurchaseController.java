package dev.lucas.desafiocdc.purchases.controllers;

import dev.lucas.desafiocdc.purchases.controllers.requests.PurchaseRequest;
import dev.lucas.desafiocdc.purchases.controllers.validators.CountryHasStateValidor;
import dev.lucas.desafiocdc.purchases.domain.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final CountryHasStateValidor countryHasStateValidor;

    @PersistenceContext
    private final EntityManager manager;

    PurchaseController(CountryHasStateValidor countryHasStateValidor, EntityManager manager){
        this.countryHasStateValidor = countryHasStateValidor;
        this.manager = manager;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(countryHasStateValidor);
    }

    @PostMapping("/new")
    public String post(@RequestBody @Valid PurchaseRequest purchaseRequest){
        Purchase purchase = purchaseRequest.toPurchase(manager);

        return purchase.toString();
    }
}
