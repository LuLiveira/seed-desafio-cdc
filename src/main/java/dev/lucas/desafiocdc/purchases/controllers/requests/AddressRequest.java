package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.configurations.validators.ExistsValue;
import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.purchases.domain.Address;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressRequest(
        @NotNull @NotEmpty String street,
        @NotNull @NotBlank String adjunct,
        @NotNull @NotBlank String city,
        @NotNull @NotEmpty @ExistsValue(domainClass = Country.class, fieldName = "id") String countryId,
        @NotNull @NotEmpty String zipCode,
        @Positive String stateId) {

    public Address toAddress(Country country) {
        return new Address(street, adjunct, city, country, zipCode);
    }
}
