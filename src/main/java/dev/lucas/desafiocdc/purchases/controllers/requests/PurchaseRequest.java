package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.configurations.validators.CPForCNPJ;
import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.coupons.domain.Coupon;
import dev.lucas.desafiocdc.coupons.repositories.CouponRepository;
import dev.lucas.desafiocdc.purchases.domain.Order;
import dev.lucas.desafiocdc.purchases.domain.Purchase;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Function;

public record PurchaseRequest(

        @Email
        String email,
        @NotNull @NotEmpty
        String name,
        @NotNull @NotEmpty
        String lastName,
        @CPForCNPJ
        String document,

        @NotNull @Valid
        AddressRequest addressRequest,

        @NotNull @NotEmpty
        String telephone,

        @Valid @NotNull
        OrderRequest orderRequest,

        //@ExistsValue(domainClass = Coupon.class, fieldName = "code") Não da pra usar por que ai vai obrigar a compra ter um coupon.
        String couponCode
) {

    public Purchase toPurchase(EntityManager manager, CouponRepository couponRepository) {
        var country = manager.find(Country.class, addressRequest.countryId());
        var address = addressRequest.toAddress(country);

        if(addressRequest.stateId() != null && !addressRequest.stateId().isEmpty()) {
            State state = manager.find(State.class, addressRequest.stateId());
            address.setState(state);
        }

        Function<Purchase, Order> orderBuild = orderRequest.toModel(manager);

        var purchase = new Purchase(
                email,
                name,
                lastName,
                document,
                telephone,
                address, orderBuild);

        if (StringUtils.hasText(couponCode)) {
            Optional<Coupon> coupon = couponRepository.findByCode(couponCode);
            coupon.ifPresent(purchase::applyCoupon);
        }

        return purchase;
    }

    public Optional<String> getCouponCode() {
        return Optional.ofNullable(couponCode);
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", addressRequest=" + addressRequest +
                ", telephone='" + telephone + '\'' +
                ", orderRequest=" + orderRequest +
                '}';
    }
}
