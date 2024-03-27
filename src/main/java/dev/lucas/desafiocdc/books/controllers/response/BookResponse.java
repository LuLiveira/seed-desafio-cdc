package dev.lucas.desafiocdc.books.controllers.response;

import dev.lucas.desafiocdc.books.domain.Book;

public record BookResponse (long id, String titulo){

    public static BookResponse fromBook(Book book){
        return new BookResponse(book.getId(),
                book.getTitle());
    }
}
