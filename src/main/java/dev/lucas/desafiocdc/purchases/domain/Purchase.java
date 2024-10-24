package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.coupons.domain.Coupon;
import jakarta.persistence.*;

import java.util.function.Function;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String lastName;
    private String document;
    private String telephone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private Order order;

    @Embedded
    private ApplyedCoupon applyedCoupon;

    public Purchase(String email, String name, String lastName, String document, String telephone, Address address, Function<Purchase, Order> orderBuild) {

        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.telephone = telephone;
        this.address = address;
        this.order = orderBuild.apply(this);
    }

    public Purchase() {
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
                ", coupon=" + applyedCoupon +
                '}';
    }

    public void applyCoupon(Coupon coupon) {
        this.applyedCoupon = new ApplyedCoupon(coupon);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getTelephone() {
        return telephone;
    }

    public Address getAddress() {
        return address;
    }

    public Order getOrder() {
        return order;
    }

    public ApplyedCoupon getApplyedCoupon() {
        return applyedCoupon;
    }
}
