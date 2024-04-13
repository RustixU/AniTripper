package com.example.AniTripper.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.AniTripper.dto.AddUserDto;
import com.example.AniTripper.services.AnimeService;
import com.example.AniTripper.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AnimeService animeService;

    @Autowired
    public UserController(UserService userService, AnimeService animeService) {
        this.userService = userService;
        this.animeService = animeService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("availableAnimes", animeService.allAnimes());

        return "user-add";
    }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/user/add";
        }
        userService.addUser(userModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.allUsers());

        return "user-all";
    }

    @GetMapping("/user-details{user-full-name}")
    public String showUserDetails(@PathVariable("user-full-name") String userFullName, Model model) {
        model.addAttribute("userDetails", userService.userInfo(userFullName));

        return "user-details";
    }

    @GetMapping("/user-delete{user-full-name}")
    public String deleteUser(@PathVariable("user-full-name") String userFullName) {
        userService.fireUser(userFullName);

        return "redirect:/users/all";
    }
}
