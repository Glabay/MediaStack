package dev.glabay.mediastack.books.service;

import dev.glabay.mediastack.books.domain.Book;
import dev.glabay.mediastack.books.integration.BookApiResponse;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-09
 */
public sealed interface IBookService permits BookServiceImpl {
    /// CRUD

    // Create
    Book createBook(BookApiResponse bookData);

    // Read
    Book getBookById(Long id);
    Book getBookByIsbn(String isbn);
    List<Book> getAllBooksByAuthor(String author);
    List<Book> getAllBooks();

    // Update
    Book updateBook(String isbn, Object updatedBookData);

    // Delete
    void deleteBookById(Long id);
}
