package dev.lucas.desafiocdc.coupons.repositories;

import dev.lucas.desafiocdc.coupons.domain.Coupon;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends org.springframework.data.repository.Repository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);
}
