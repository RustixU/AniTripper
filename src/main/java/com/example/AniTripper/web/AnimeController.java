package com.example.AniTripper.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.AniTripper.dto.AddAnimeDto;
import com.example.AniTripper.services.AnimeService;

@Controller
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping("/add")
    public String addAnime() {
        return "anime-add";
    }

    @ModelAttribute("animeModel")
    public AddAnimeDto initAnime() {
        return new AddAnimeDto();
    }

    @PostMapping("/add")
    public String addAnime(@Valid AddAnimeDto animeModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("animeModel", animeModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.animeModel",
                    bindingResult);
            return "redirect:/animes/add";
        }
        animeService.addAnime(animeModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllAnimes(Model model) {
        model.addAttribute("animeInfos", animeService.allAnimes());

        return "anime-all";
    }

    @GetMapping("/anime-details/{anime-name}")
    public String animeDetails(@PathVariable("anime-name") String animeName, Model model) {
        model.addAttribute("animeDetails", animeService.animeDetails(animeName));

        return "anime-details";
    }

    @GetMapping("/anime-delete/{anime-name}")
    public String deleteAnime(@PathVariable("anime-name") String animeName) {
        animeService.removeAnime(animeName);

        return "redirect:/animes/all";
    }
}
