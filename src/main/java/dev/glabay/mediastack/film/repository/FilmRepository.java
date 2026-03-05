package dev.glabay.mediastack.film.repository;

import dev.glabay.mediastack.film.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByImdbId(String imdbId);
    List<Film> findByTitleStartingWithIgnoreCase(String title);
}
