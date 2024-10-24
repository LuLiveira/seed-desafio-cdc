package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.*;

@Entity
public class Address {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String street;
    private  String adjunct;
    private  String city;

    @ManyToOne
    private  Country country;

    @ManyToOne
    private State state;

    private  String zipCode;

    public Address(String street, String adjunct, String city, Country country, String zipCode) {

        this.street = street;
        this.adjunct = adjunct;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public void setState(State state) {
        if(!state.belongTo(this.country)) {
            throw new IllegalStateException("This state not belong to this country: " + state + " " + this.country);
        }
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getAdjunct() {
        return adjunct;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public State getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", adjunct='" + adjunct + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", state=" + state +
                '}';
    }
}
