package dev.glabay.mediastack.game.repository;

import dev.glabay.mediastack.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByGameBrainId(Long gameBrainId);

    void deleteByGameBrainId(Long gameBrainId);
}
