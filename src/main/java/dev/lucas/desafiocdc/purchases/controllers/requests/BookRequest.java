package dev.lucas.desafiocdc.purchases.controllers.requests;

import dev.lucas.desafiocdc.books.domain.Book;
import dev.lucas.desafiocdc.configurations.validators.ExistsValue;
import dev.lucas.desafiocdc.purchases.domain.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record BookRequest(

        @ExistsValue(domainClass = Book.class, fieldName = "id")
        int bookId,

        @Positive @Min(1)
        int quantity
) {
        public OrderItem toModel(EntityManager manager) {
                var book = manager.find(Book.class, bookId);

                return new OrderItem(book, quantity);
        }
}
