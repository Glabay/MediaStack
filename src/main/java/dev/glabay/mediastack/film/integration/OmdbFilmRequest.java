package dev.glabay.mediastack.film.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OmdbFilmRequest(
    @JsonProperty("imdbID") String imdbId,
    @JsonProperty("Title") String title,
    @JsonProperty("Released") String released,
    @JsonProperty("Type") String type,
    @JsonProperty("Poster") String posterUrl,
    @JsonProperty("Genre") String genres,
    @JsonProperty("imdbRating") String imdbRating
) {
}
