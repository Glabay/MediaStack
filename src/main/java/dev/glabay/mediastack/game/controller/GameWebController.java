package dev.glabay.mediastack.game.controller;

import dev.glabay.mediastack.game.service.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-13
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameWebController {
    private final GameServiceImpl service;

    @GetMapping
    public String getGameIndex() {
        return "games";
    }

    @GetMapping("/view")
    public String getGameById(Model model) {
        var games = service.getAllGames();
        model.addAttribute("games", games);
        return "games-view";
    }
}