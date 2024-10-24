package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.coupons.domain.Coupon;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Embeddable
public class ApplyedCoupon {

    @ManyToOne
    private Coupon coupon;

    @Column(nullable = true)
    private int percentage;
    private LocalDate expiration;

    public ApplyedCoupon(Coupon coupon) {
        this.coupon = coupon;
        this.percentage = coupon.getPercentage();
        this.expiration = coupon.getExpiration();
    }

    public ApplyedCoupon() {
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "ApplyedCoupon{" +
                "code=" + coupon.getCode() +
                ", percentage=" + percentage +
                ", expiration=" + expiration +
                '}';
    }
}
