package dev.glabay.mediastack.film.controller;

import dev.glabay.mediastack.film.domain.Film;
import dev.glabay.mediastack.film.service.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmController {
    private final FilmServiceImpl filmService;

    @GetMapping("/{title}")
    public ResponseEntity<Film> fetchFilmByTitle(@PathVariable String title) {
        var film = filmService.fetchFilmByTitle(title);
        return ResponseEntity.ok(film);
    }
}
