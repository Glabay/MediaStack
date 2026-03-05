package dev.glabay.mediastack.film.controller;

import dev.glabay.mediastack.film.domain.Film;
import dev.glabay.mediastack.film.service.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmViewController {
    private final FilmServiceImpl filmService;

    @GetMapping
    public String getFilms(Model model) {
        List<Film> listOfMedia = filmService.getAllFilms();
        model.addAttribute("films", listOfMedia);
        return "films";
    }
}
