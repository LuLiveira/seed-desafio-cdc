package dev.lucas.desafiocdc.purchases.controllers.validators;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.purchases.controllers.requests.PurchaseRequest;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryHasStateValidor implements Validator {

    @PersistenceContext
    EntityManager manager;

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

        var country = manager.find(Country.class,
                Long.valueOf(request.addressRequest().countryId()));

        if (country.hasStates()) {
            if (request.addressRequest().stateId().isEmpty()){
                errors.rejectValue("addressRequest.stateId", null, "stateId cannot be null in this case");
                return;
            }

            var state = manager.find(State.class, Long.valueOf(request.addressRequest().stateId()));
            if (state == null) {
                errors.rejectValue("addressRequest.stateId", null, "State is invalid");

            } else if (!state.stateBelongTo(country)) {
                errors.rejectValue("addressRequest.stateId", null, "State cannot belong to " + country.getName());
            }
        }
    }
}
