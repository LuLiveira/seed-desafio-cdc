package dev.lucas.desafiocdc.autores.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Nome nome;

    @Embedded
    private Email email;

    @Column
    private String descricao;

    private Instant momento;

    public String getDescricao() {
        return descricao;
    }

    public Email getEmail() {
        return email;
    }

    public Instant getMomento() {
        return momento;
    }

    public Nome getNome() {
        return nome;
    }

    public Autor(){}

    private Autor(Nome nome, Email email, String descricao){
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.momento = Instant.now();
    }

    public static Autor of (String nome, String email, String descricao) {
        var nomeVo = Nome.of(nome);
        var emailVo = Email.of(email);

        return new Autor(nomeVo, emailVo, descricao);
    }
}
