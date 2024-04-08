package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;

public class Address {
    private final String street;
    private final String adjunct;
    private final String city;
    private final String country;
    private String state;

    public Address(String street, String adjunct, String city, String countryId, String stateId) {

        this.street = street;
        this.adjunct = adjunct;
        this.city = city;
        this.country = countryId;
        this.state = stateId;
    }
}
