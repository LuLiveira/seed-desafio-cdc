package dev.lucas.desafiocdc.purchases.domain;

public class Purchase {
    private final String email;
    private final String name;
    private final String lastName;
    private final String document;
    private final String telephone;
    private final Address address;

    public Purchase(String email, String name, String lastName, String document, String telephone, Address address) {

        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.telephone = telephone;
        this.address = address;
    }
}
