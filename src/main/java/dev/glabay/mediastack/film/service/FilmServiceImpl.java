package dev.glabay.mediastack.film.service;

import dev.glabay.mediastack.common.media.MediaType;
import dev.glabay.mediastack.film.domain.Film;
import dev.glabay.mediastack.film.integration.OmdbFilmRequest;
import dev.glabay.mediastack.film.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
@Service
@RequiredArgsConstructor
public final class FilmServiceImpl implements IFilmService {
    private final FilmRepository filmRepository;
    private final RestClient restClient;

    @Value("${mediastack.omdb.api.url}")
    private String baseApiUrl;

    @Value("${mediastack.omdb.api.key}")
    private String apiKey;

    @Override
    public Film createFilm(OmdbFilmRequest data) {
        var film = new Film();
            film.setMediaType(MediaType.MOVIE);
            film.setImdbId(data.imdbId());
            film.setTitle(data.title());
            film.setReleased(data.released());
            film.setType(data.type());
            film.setPosterUrl(data.posterUrl());
            film.setGenres(data.genres());
            film.setImdbRating(Double.valueOf(data.imdbRating()));
        return filmRepository.save(film);
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() ->
            new RuntimeException("Film not found"));
    }

    @Override
    public Film getFilmByImdbId(String imdbId) {
        return filmRepository.findByImdbId(imdbId).orElseThrow(() ->
            new RuntimeException("Film not found"));
    }

    @Override
    public List<Film> getAllFilmsByTitle(String title) {
        return filmRepository.findByTitleStartingWithIgnoreCase(title);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film updateFilm(String imdbId, Object updatedData) {
        var data = (OmdbFilmRequest) updatedData;
        var film = getFilmByImdbId(imdbId);
            film.setImdbId(data.imdbId());
            film.setTitle(data.title());
            film.setReleased(data.released());
            film.setType(data.type());
            film.setPosterUrl(data.posterUrl());
            film.setGenres(data.genres());
            film.setImdbRating(Double.valueOf(data.imdbRating()));
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }


    public Film fetchFilmByTitle(String title) {
        var formattedTitle = title.replaceAll(" ", "+");
        var reply = restClient.get()
            .uri(baseApiUrl + "?apikey={apiKey}&t={title}", apiKey, formattedTitle)
            .retrieve()
            .body(new ParameterizedTypeReference<OmdbFilmRequest>() {});
        return createFilm(reply);
    }
}
