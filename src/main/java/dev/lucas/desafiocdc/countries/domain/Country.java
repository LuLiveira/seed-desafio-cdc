package dev.lucas.desafiocdc.countries.domain;

import dev.lucas.desafiocdc.states.domain.State;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<State> states;

    public Country(String name) {
        this.name = name;
    }

    public Country(Long id) {
        this.id = id;
    }
}
