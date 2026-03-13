package dev.glabay.mediastack.game.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GameBrainDto(
    Long id,
    Long year,
    String name,
    String genre,
    String image,
    String link,
    GameBrainRating rating,
    @JsonProperty("short_description") String shortDescription
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GameBrainRating(
        Double mean,
        Long count
    ) {}
}
