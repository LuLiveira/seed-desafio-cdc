package dev.lucas.desafiocdc.states.controllers.request;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ValidCountryValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidCountry {

    String message() default "Invalid country ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
