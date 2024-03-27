package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.configurations.validators.CPForCNPJ;
import dev.lucas.desafiocdc.purchases.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PersonRequest(

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
        String telephone

) {

    public Person toPerson(EntityManager manager) {
        var address = addressRequest.toAddress(manager);

        return new Person(
                email,
                name,
                lastName,
                document,
                telephone,
                address
        );
    }

}
