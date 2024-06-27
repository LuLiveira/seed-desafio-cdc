package dev.lucas.desafiocdc.purchases.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "tbl_order") //Dado que "ORDER" é uma palavra reservada do SQL, o ORM quebra ao utiliza-la para nome da tabela
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private final Purchase purchase;

    @ElementCollection
    private final Set<OrderItem> orderItems;

    public Order(Purchase purchase, Set<OrderItem> orderItems) {
        this.purchase = purchase;
        this.orderItems = orderItems;
    }

    public boolean isValidTotal(@NotNull @Positive @Min(1) BigDecimal total) {
        var totalOrder = orderItems.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, (actualValue, nextValue) -> actualValue.add(nextValue));
        return totalOrder.doubleValue() == totalOrder.doubleValue();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderItems=" + orderItems +
                '}';
    }
}
