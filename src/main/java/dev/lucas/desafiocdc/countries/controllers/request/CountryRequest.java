package dev.lucas.desafiocdc.countries.controllers.request;

import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import dev.lucas.desafiocdc.countries.domain.Country;
import jakarta.validation.constraints.NotNull;

public record CountryRequest(
        @NotNull @UniqueValue(fieldName = "name",
                domainClass = Country.class) String name) {
    public Country toCoutry() {

        return new Country(this.name);
    }
}
