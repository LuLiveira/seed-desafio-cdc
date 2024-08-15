package dev.lucas.desafiocdc.purchases.controllers.validators;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.coupons.domain.Coupon;
import dev.lucas.desafiocdc.coupons.repositories.CouponRepository;
import dev.lucas.desafiocdc.purchases.controllers.requests.PurchaseRequest;
import dev.lucas.desafiocdc.states.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CouponIsValidValidator implements Validator {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        var request = (PurchaseRequest) target;

        var code = request.getCouponCode();
        if(code.isEmpty()){
            return;
        }
        var coupon = couponRepository.findByCode(code.get());
        if (coupon.isEmpty()) {
            errors.rejectValue("couponCode", null, "coupon does not exists");
            return;
        }
        if (!coupon.get().isValid()){
            errors.rejectValue("couponCode", null, "couponCode is invalid");
        };


    }
}
