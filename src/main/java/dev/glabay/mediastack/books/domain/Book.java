package dev.glabay.mediastack.books.domain;

import dev.glabay.mediastack.common.media.MediaItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-02-09
 */
@Getter
@Setter
@ToString
@Entity(name = "BOOK")
@Table(
    name = "BOOKS",
    uniqueConstraints = @UniqueConstraint(columnNames = "isbn")
)
public class Book extends MediaItem {

    @Column(nullable = false, unique = true)
    private String isbn;
    private String author;
    private Integer totalNumberOfPages;
    private String publicationYear;
    private String bookCover;
}
