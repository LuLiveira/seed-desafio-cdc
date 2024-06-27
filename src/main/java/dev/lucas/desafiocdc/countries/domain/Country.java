package dev.lucas.desafiocdc.countries.domain;

import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<State> states;

    public String getName() {
        return name;
    }

    public List<State> getStates() {
        return states;
    }

    public Country(String name) {
        this.name = name;
    }
    public Country() {
    }


    public Country(Long id) {
        this.id = id;
    }

    public boolean hasStates() {
        return !states.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
