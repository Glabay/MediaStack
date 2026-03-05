package dev.glabay.mediastack.books.controller;

import dev.glabay.mediastack.books.domain.Book;
import dev.glabay.mediastack.books.dto.IsnRequest;
import dev.glabay.mediastack.books.service.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String handleBookIsbn(@Valid @ModelAttribute IsnRequest request,
                                 BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "books";

        bookService.getBookByIsbn(request.isbn());
        return "redirect:/books";
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }
}
