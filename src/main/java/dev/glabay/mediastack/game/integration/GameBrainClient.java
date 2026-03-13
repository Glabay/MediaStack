package dev.glabay.mediastack.game.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@Service
@RequiredArgsConstructor
public class GameBrainClient {
    private final RestClient restClient;

    @Value( "${gamebrain.api.url}")
    private String baseUrl;

    @Value( "${gamebrain.api.key}")
    private String apiKey;

    public GameBrainGameResponse getGameByQuery(String gameSearch) {
        return restClient.get()
            .uri(baseUrl + "/games?query={gameSearch}", gameSearch)
            .header("x-api-key", apiKey)
            .retrieve()
            .body(GameBrainGameResponse.class);
    }
}
