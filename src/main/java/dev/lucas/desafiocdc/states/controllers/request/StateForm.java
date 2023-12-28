package dev.lucas.desafiocdc.states.controllers.request;

import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StateForm (
        @NotNull @UniqueValue(domainClass = State.class, fieldName = "name") String name,
        @Positive Long countryId
) {
}
