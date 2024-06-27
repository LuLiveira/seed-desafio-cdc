package dev.lucas.desafiocdc.states.domain;

import dev.lucas.desafiocdc.countries.domain.Country;
import jakarta.persistence.*;

@Entity
public class State {

    @Deprecated
    public State() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Country country;

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public boolean belongTo(Country country) {
        return this.country.equals(country);
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
