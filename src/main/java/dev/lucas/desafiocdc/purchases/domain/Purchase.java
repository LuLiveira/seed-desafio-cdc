package dev.lucas.desafiocdc.purchases.domain;

import jakarta.persistence.*;

import java.util.function.Function;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String email;
    private final String name;
    private final String lastName;
    private final String document;
    private final String telephone;

    @ManyToOne
    private final Address address;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private final Order order;

    public Purchase(String email, String name, String lastName, String document, String telephone, Address address, Function<Purchase, Order> orderBuild) {

        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.telephone = telephone;
        this.address = address;
        this.order = orderBuild.apply(this);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                ", order=" + order +
                '}';
    }
}
