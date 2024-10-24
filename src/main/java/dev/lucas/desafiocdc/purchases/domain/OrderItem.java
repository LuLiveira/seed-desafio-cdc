package dev.lucas.desafiocdc.purchases.domain;

import dev.lucas.desafiocdc.books.domain.Book;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class OrderItem {


    @ManyToOne
    private Book book;
    private int quantity;
    private BigDecimal actualPrice;

    public OrderItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.actualPrice = book.getPrice();
    }

    public OrderItem() {
    }

    public BigDecimal getTotal() {
        return actualPrice.multiply(new BigDecimal(quantity));

    }

    public int getQuantity() {
        return quantity;
    }

    public Book getBook() {
        return book;
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
