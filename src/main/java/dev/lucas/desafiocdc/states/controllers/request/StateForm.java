package dev.lucas.desafiocdc.states.controllers.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.lucas.desafiocdc.configurations.validators.UniqueValue;
import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StateForm (
        @NotNull @UniqueValue(domainClass = State.class, fieldName = "name") String name,

        //Aqui o Otavio usou uma anotação mais generica "ExistsId" que é capaz de validar se existe um registro
        // baseado em seu id.
        @NotNull @Positive @ValidCountry Long countryId

) {
    public State toState(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, this.countryId);

        return new State(this.name, country);
    }
}
