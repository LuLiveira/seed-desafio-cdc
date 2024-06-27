package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.countries.domain.Country;
import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String street;
    private final String adjunct;
    private final String city;

    @ManyToOne
    private final Country country;

    @OneToOne
    private State state;

    public Address(String street, String adjunct, String city, Country country) {

        this.street = street;
        this.adjunct = adjunct;
        this.city = city;
        this.country = country;
    }

    public void setState(State state) {
        if(!state.belongTo(this.country)) {
            throw new IllegalStateException("This state not belong to this country: " + state + " " + this.country);
        }
        this.state = state;
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
