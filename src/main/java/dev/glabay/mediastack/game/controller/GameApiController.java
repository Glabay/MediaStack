package dev.glabay.mediastack.game.controller;

import dev.glabay.mediastack.game.integration.GameBrainDto;
import dev.glabay.mediastack.game.integration.GameBrainGameResponse;
import dev.glabay.mediastack.game.service.GameServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameApiController {
    private final GameServiceImpl service;

    @GetMapping("/query")
    public ResponseEntity<GameBrainGameResponse> getGameByQuery(@RequestParam String gameSearch) {
        var result = service.getGameByQuery(gameSearch);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> persistThisGame(@Valid @RequestBody GameBrainDto dto,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            IO.println(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().build();
        }
        service.createGame(dto);
        return ResponseEntity.ok().build();
    }
}
