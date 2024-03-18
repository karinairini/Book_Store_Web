package com.example.bookstoreweb.service;

import com.example.bookstoreweb.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Book addBook(Book book);

    void deleteBook(Book book);

    Optional<Book> findBookById(Integer id);

    Book updateBook(Book book);
}
