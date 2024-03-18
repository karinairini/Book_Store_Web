package com.example.bookstoreweb.repository;

import com.example.bookstoreweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findById(Integer id);
}
