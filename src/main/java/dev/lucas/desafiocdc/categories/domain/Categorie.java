package dev.lucas.desafiocdc.categories.domain;

import jakarta.persistence.*;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    private Categorie(String name) {
        this.name = name;
    }

    public Categorie(){}

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public static Categorie of(String name) {
        return new Categorie(name);
    }
}
