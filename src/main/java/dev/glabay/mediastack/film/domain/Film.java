package dev.glabay.mediastack.film.domain;

import dev.glabay.mediastack.common.media.MediaItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
@Getter
@Setter
@Entity
@Table(
    name = "FILMS",
    uniqueConstraints = @UniqueConstraint(columnNames = "imdbId")
)
public class Film extends MediaItem {

    @Column(nullable = false, unique = true)
    private String imdbId;
    private String title;
    private String released;
    private String type;
    private String posterUrl;
    private String genres;

    private Double imdbRating;

    private Instant addedAt;

    @PrePersist
    private void prePersist() {
        this.addedAt = Instant.now();
    }
}
