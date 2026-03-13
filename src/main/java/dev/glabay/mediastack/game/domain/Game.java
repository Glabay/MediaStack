package dev.glabay.mediastack.game.domain;

import dev.glabay.mediastack.common.media.MediaItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@Getter
@Setter
@Entity
@Table(
    name = "GAMES",
    uniqueConstraints = @UniqueConstraint(columnNames = "game_brain_id")
)
public class Game extends MediaItem {

    @Column(
        name = "game_brain_id",
        nullable = false,
        unique = true
    )
    private Long gameBrainId;
    private Long releaseYear;

    private String title;
    private String genre;
    private String image;
    private String gameBrainUrl;
    private String gameDescription;

    private Double gameBrainRating;

    private Instant addedAt;

    @PrePersist
    private void prePersist() {
        this.addedAt = Instant.now();
    }

}
