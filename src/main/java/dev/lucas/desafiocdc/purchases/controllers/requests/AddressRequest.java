package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.configurations.validators.ExistsValue;
import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.purchases.domain.Address;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressRequest(
        @NotNull @NotEmpty String street,
        @NotNull @NotBlank String adjunct,
        @NotNull @NotBlank String city,
        @NotNull @NotEmpty @ExistsValue(domainClass =
                Country.class, fieldName = "id")
        String countryId,
        @NotNull @NotEmpty String zipCode,
        @Positive String stateId) {

    public Address toAddress(EntityManager manager) {
        var country = manager.find(Country.class,
                Long.valueOf(this.countryId));
        var states = country.getStates();

        if (states.isEmpty()) {
            return new Address(street, adjunct, city, country
            );
        } else if (stateId != null) {
            return new Address(street, adjunct, city, country,
                    states.stream().filter(state -> state.getId().equals(Long.valueOf(stateId))).findFirst().get()
            );
        } else {
            throw new IllegalStateException("stateId " +
                    "cannot" + " be null or empty");
        }
    }
}
