package dev.glabay.mediastack.books.controller;

import dev.glabay.mediastack.books.domain.Book;
import dev.glabay.mediastack.books.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-13
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookViewController {
    private final BookServiceImpl bookService;

    @GetMapping
    public String getBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();

        model.addAttribute("books", bookList);
        return "books";
    }
}
