package dev.glabay.mediastack.books.controller;

import dev.glabay.mediastack.books.domain.Book;
import dev.glabay.mediastack.books.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-10
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookServiceImpl bookService;

    @PostMapping
    public String handleBookIsbn(@RequestParam String isbn) {
        if (isbn.isBlank() || !isbn.matches("\\d{10,13}"))
            return "redirect:/books";
        bookService.getBookByIsbn(isbn);
        return "redirect:/books";
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }
}
