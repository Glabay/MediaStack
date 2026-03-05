package dev.glabay.mediastack.film.service;

import dev.glabay.mediastack.film.domain.Film;
import dev.glabay.mediastack.film.integration.OmdbFilmRequest;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
public sealed interface IFilmService permits FilmServiceImpl {
    /// CRUD

    // Create
    Film createFilm(OmdbFilmRequest data);

    // Read
    Film getFilmById(Long id);
    Film getFilmByImdbId(String imdbId);
    List<Film> getAllFilmsByTitle(String title);
    List<Film> getAllFilms();

    // Update
    Film updateFilm(String imdbId, Object updatedData);

    // Delete
    void deleteFilmById(Long id);
}
