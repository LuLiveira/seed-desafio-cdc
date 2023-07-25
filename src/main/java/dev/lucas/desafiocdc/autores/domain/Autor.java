package dev.lucas.desafiocdc.autores.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Embedded
    private String nome;

    // @Embedded
    private String email;

    private String descricao;

    private Instant momento;

    public String getDescricao() {
        return descricao;
    }

    public String getEmail() {
        return email;
    }

    public Instant getMomento() {
        return momento;
    }

    public String getNome() {
        return nome;
    }

    public Autor(){}

    private Autor(String nome, String email, String descricao){
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.momento = Instant.now();
    }

    public static Autor of (String nome, String email, String descricao) {
        return new Autor(nome, email, descricao);
    }
}
