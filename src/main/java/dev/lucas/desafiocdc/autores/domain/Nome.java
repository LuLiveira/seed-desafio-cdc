package dev.lucas.desafiocdc.autores.domain;

import org.springframework.util.Assert;

import jakarta.persistence.Column;

public class Nome {

    @Column
    private String nome;
    
    public String getNome() {
        return nome;
    }
    
    public Nome(){}

    private Nome (String nome){
        this.nome = nome;
    }

    public static Nome of(String name) {
        Assert.notNull(name, "Nome inválido");
        return new Nome(name);
    }


}
