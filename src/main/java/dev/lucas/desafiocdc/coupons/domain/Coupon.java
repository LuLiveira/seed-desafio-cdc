package dev.lucas.desafiocdc.coupons.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Coupon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String code;
    private int percentage;
    private LocalDate expiration;

    @Deprecated
    public Coupon() {}

    public Coupon(String code, int percentage, LocalDate expiration) {
        this.code = code;
        this.percentage = percentage;
        this.expiration = expiration;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getPercentage() {
        return percentage;
    }

    public LocalDate getExpiration() {
        return expiration;
    }
}
