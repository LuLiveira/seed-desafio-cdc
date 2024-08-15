package dev.lucas.desafiocdc.coupons.controllers.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import dev.lucas.desafiocdc.coupons.domain.Coupon;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record CouponRequest(
        @NotNull
        @NotEmpty
        @UniqueValue(fieldName = "code", domainClass = Coupon.class)
        String code,
        @Positive
        @Min(value = 0)
        int percentage,
        @Future
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
        LocalDate expiration) {
        public Coupon toCoupon() {
                return new Coupon(code, percentage, expiration);
        }
}
