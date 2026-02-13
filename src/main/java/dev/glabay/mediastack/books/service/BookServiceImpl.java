package dev.glabay.mediastack.books.service;

import dev.glabay.mediastack.books.domain.Book;
import dev.glabay.mediastack.books.integration.BookApiResponse;
import dev.glabay.mediastack.books.repository.BookRepository;
import dev.glabay.mediastack.common.media.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-09
 */
@Service
@RequiredArgsConstructor
public final class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final RestClient restClient;

    @Override
    public Book createBook(BookApiResponse bookData) {
        var isbn = bookData.books().keySet().iterator().next();
        var dto = bookData.books().get(isbn);
        var book = new Book();
            book.setTitle(dto.title());
            book.setMediaType(MediaType.BOOK);
            book.setIsbn(isbn);
            book.setAuthor(dto.authors().getFirst().name());
            book.setTotalNumberOfPages(dto.numberOfPages());
            book.setPublicationYear(dto.publicationDate());
            book.setBookCover(dto.cover().medium());
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbnIgnoreCase(isbn)
            .orElseGet(() -> {
                var books = restClient.get()
                    .uri("https://openlibrary.org/api/books?bibkeys={isbn}&format=json&jscmd=data", isbn)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, BookApiResponse.Book>>() {});
                var book = createBook(new BookApiResponse(books));

                return bookRepository.save(book);
            });
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.copyOf(bookRepository.findAll());
    }

    @Override
    public Book updateBook(String isbn, Object updatedBookData) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
    }
}
