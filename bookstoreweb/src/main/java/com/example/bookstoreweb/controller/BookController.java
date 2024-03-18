package com.example.bookstoreweb.controller;

import com.example.bookstoreweb.model.Book;
import com.example.bookstoreweb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("title", "Books");
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String displayAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String processAddBookForm(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook")
    public String displayDeleteBookForm(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "deleteBook";
    }

    @PostMapping("/deleteBook")
    public String processDeleteBookForm(@RequestParam(name = "bookId", required = false) Integer bookId) {
        if (bookId != null) {
            Book bookToDelete = bookService.findBookById(bookId).get();
            if (bookToDelete != null) {
                bookService.deleteBook(bookToDelete);
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/updateBook")
    public String displayUpdateBookForm(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String processUpdateBookForm(@RequestParam(name = "bookId", required = false) Integer bookId, @ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
}
