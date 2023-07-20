package dev.lucas.desafiocdc.autores.domain;


public class Email {

    private String email;

    public Email(){}

    private Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public static Email of(String email) {
        //Alguma validação
        return new Email(email);
    }

}
