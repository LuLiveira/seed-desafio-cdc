package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.coupons.domain.Coupon;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Embeddable
public class ApplyedCoupon {

    @ManyToOne
    private Coupon coupon;

    private final int percentage;
    private final LocalDate expiration;

    public ApplyedCoupon(Coupon coupon) {
        this.coupon = coupon;
        this.percentage = coupon.getPercentage();
        this.expiration = coupon.getExpiration();
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
