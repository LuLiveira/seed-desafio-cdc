package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;

public class Address {
    private final String street;
    private final String adjunct;
    private final String city;
    private final Country country;
    private State state;

    public Address(String street, String adjunct, String city, Country country, State state) {

        this.street = street;
        this.adjunct = adjunct;
        this.city = city;
        this.country = country;
        this.state = state;
    }

    public Address(String street, String adjunct, String city, Country country) {

        this.street = street;
        this.adjunct = adjunct;
        this.city = city;
        this.country = country;
    }
}
