package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.purchases.domain.Order;
import dev.lucas.desafiocdc.purchases.domain.OrderItem;
import dev.lucas.desafiocdc.purchases.domain.Purchase;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public record OrderRequest(
        @NotNull @Positive @Min(1)
        BigDecimal total, //poderias ser BigDecimal

        @Valid @Size(min = 1)
        List<BookRequest> items
) {

    @Override
    public String toString() {
        return "CartRequest{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }


    public Function<Purchase, Order> toModel(EntityManager manager) {
        Set<OrderItem> orderItems = items.stream().map(order -> order.toModel(manager)).collect(Collectors.toSet());

        return (purchase) -> {

            var order = new Order(purchase, orderItems);

            if (!order.isValidTotal(total)) {
                throw new IllegalStateException("Total is invalid");
            }

            return order;
        };
    }
}
