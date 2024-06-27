package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.books.domain.Book;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class OrderItem {


    @ManyToOne
    private final Book book;
    private final int quantity;
    private final BigDecimal actualPrice;

    public OrderItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.actualPrice = book.getPrice();
    }

    public BigDecimal getTotal() {
        return actualPrice.multiply(new BigDecimal(quantity));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(book, orderItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(book);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "book=" + book +
                ", quantity=" + quantity +
                ", actualPrice=" + actualPrice +
                '}';
    }
}
