package dev.glabay.mediastack.books.repository;

import dev.glabay.mediastack.books.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-09
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbnIgnoreCase(String isbn);
    List<Book> findByAuthorIgnoreCase(String author);
}
