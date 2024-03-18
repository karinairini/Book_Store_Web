package com.example.bookstoreweb.service;

import com.example.bookstoreweb.model.Book;
import com.example.bookstoreweb.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
