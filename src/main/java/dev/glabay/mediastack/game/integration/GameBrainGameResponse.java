package dev.glabay.mediastack.game.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GameBrainGameResponse(
    List<GameBrainDto> results
) {}
