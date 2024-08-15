package dev.lucas.desafiocdc.coupons.controllers;

import dev.lucas.desafiocdc.coupons.controllers.request.CouponRequest;
import dev.lucas.desafiocdc.coupons.domain.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @PersistenceContext
    private final EntityManager entityManager;

    public CouponController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/new")
    @Transactional
    public ResponseEntity<Coupon> post(@Valid @RequestBody CouponRequest couponRequest) {
        Coupon coupon = couponRequest.toCoupon();
        entityManager.persist(coupon);
        return ResponseEntity.status(201).body(coupon);
    }
}
