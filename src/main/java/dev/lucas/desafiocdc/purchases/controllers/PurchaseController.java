package dev.lucas.desafiocdc.purchases.controllers;

import dev.lucas.desafiocdc.coupons.repositories.CouponRepository;
import dev.lucas.desafiocdc.purchases.controllers.requests.PurchaseRequest;
import dev.lucas.desafiocdc.purchases.controllers.validators.CountryHasStateValidor;
import dev.lucas.desafiocdc.purchases.controllers.validators.CouponIsValidValidator;
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
    private final CouponRepository couponRepository;

    @PersistenceContext
    private final EntityManager manager;
    private final CouponIsValidValidator couponIsValidValidator;

    PurchaseController(CountryHasStateValidor countryHasStateValidor, CouponIsValidValidator couponIsValidValidator, CouponRepository couponRepository, EntityManager manager){
        this.countryHasStateValidor = countryHasStateValidor;
        this.couponIsValidValidator = couponIsValidValidator;
        this.couponRepository = couponRepository;
        this.manager = manager;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(countryHasStateValidor);
        binder.addValidators(couponIsValidValidator);
    }

    @PostMapping("/new")
    public String post(@RequestBody @Valid PurchaseRequest purchaseRequest){
        Purchase purchase = purchaseRequest.toPurchase(manager, couponRepository);

        return purchase.toString();
    }
}
