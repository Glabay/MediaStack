package dev.glabay.mediastack.game.service;

import dev.glabay.mediastack.game.domain.Game;
import dev.glabay.mediastack.game.integration.GameBrainDto;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
public sealed interface IGameService permits GameServiceImpl {
    /// CRUD

    // Create
    Game createGame(GameBrainDto gameData);

    // Read
    Game getGameByGameBrainId(Long id);
    List<Game> getAllGames();

    // Update
    Game updateGame(GameBrainDto updatedData);

    // Delete
    void deleteGameById(Long id);
    void deleteGameByGameBrainId(Long id);

}
