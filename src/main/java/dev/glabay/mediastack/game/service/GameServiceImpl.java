package dev.glabay.mediastack.game.service;

import dev.glabay.mediastack.common.media.MediaType;
import dev.glabay.mediastack.game.domain.Game;
import dev.glabay.mediastack.game.integration.GameBrainClient;
import dev.glabay.mediastack.game.integration.GameBrainDto;
import dev.glabay.mediastack.game.integration.GameBrainGameResponse;
import dev.glabay.mediastack.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@Service
@RequiredArgsConstructor
public final class GameServiceImpl implements IGameService {
    private final GameRepository repository;
    private final GameBrainClient gameBrainClient;

    @Override
    public Game createGame(GameBrainDto gameData) {
        var game = new Game();
            game.setMediaType(MediaType.GAME);
            game.setGameBrainId(gameData.id());
            game.setReleaseYear(gameData.year());
            game.setTitle(gameData.name());
            game.setGenre(gameData.genre());
            game.setImage(gameData.image());
            game.setGameBrainUrl(gameData.link());
            game.setGameDescription(gameData.shortDescription());
            game.setGameBrainRating(gameData.rating().mean());
        return repository.save(game);
    }

    @Override
    public Game getGameByGameBrainId(Long id) {
        return repository.findByGameBrainId(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Game with gameBrainId " + id + " not found"));
    }

    @Override
    public List<Game> getAllGames() {
        return repository.findAll();
    }

    @Override
    public Game updateGame(GameBrainDto updatedData) {
        var cachedGame = getGameByGameBrainId(updatedData.id());
            cachedGame.setGameBrainId(updatedData.id());
            cachedGame.setReleaseYear(updatedData.year());
            cachedGame.setTitle(updatedData.name());
            cachedGame.setGenre(updatedData.genre());
            cachedGame.setImage(updatedData.image());
            cachedGame.setGameBrainUrl(updatedData.link());
            cachedGame.setGameDescription(updatedData.shortDescription());
            cachedGame.setGameBrainRating(updatedData.rating().mean());

        return repository.save(cachedGame);
    }

    @Override
    public void deleteGameById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteGameByGameBrainId(Long id) {
        repository.deleteByGameBrainId(id);
    }

    public GameBrainGameResponse getGameByQuery(String gameSearch) {
        return gameBrainClient.getGameByQuery(gameSearch);
    }
}