package com.example.bookstoreweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private String author;

    @NonNull
    private Double price;

    private LocalDate publishedDate;
}
