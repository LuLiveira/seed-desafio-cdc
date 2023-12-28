package dev.lucas.desafiocdc.configurations.validators;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    EntityManager manager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " =:value");
        query.setParameter("value", value);
        List<?> resultList = query.getResultList();
//        Assert.state(resultList.isEmpty(),
//                "Found most than one " + klass + " with attribute " + domainAttribute + "=" + value);
        return resultList.isEmpty();
    }
}
