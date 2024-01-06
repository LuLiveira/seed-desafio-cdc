package dev.lucas.desafiocdc.states.controllers.request;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidCountryValidator implements ConstraintValidator<ValidCountry, Object> {

    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(ValidCountry params) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " +
                "Country where id " +
                "=:value");
        query.setParameter("value", value);
        List<?> resultList = query.getResultList();
        return !resultList.isEmpty();
    }
}
