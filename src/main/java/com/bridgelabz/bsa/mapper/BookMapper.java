package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.BookRequest;
import com.bridgelabz.bsa.dto.BookResponse;
import com.bridgelabz.bsa.model.Book;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookMapper {

    public Book mapToBook(BookRequest bookRequest, Book book) {
        book.setBookName(bookRequest.getBookName());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setDescription(bookRequest.getDescription());
        try {
            book.setLogo(bookRequest.getLogo().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        return book;
    }

    public BookResponse mapToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setBookName(bookResponse.getBookName());
        bookResponse.setAuthorName(book.getAuthorName());
        bookResponse.setDescription(bookResponse.getDescription());
        bookResponse.setLogo(bookResponse.getLogo());
        bookResponse.setPrice(bookResponse.getPrice());
        bookResponse.setQuantity(bookResponse.getQuantity());
        return bookResponse;
    }
}
