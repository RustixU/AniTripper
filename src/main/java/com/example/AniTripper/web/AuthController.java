package com.example.AniTripper.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.AniTripper.dto.AccountRegistrationDto;
import com.example.AniTripper.models.entities.Account;
import com.example.AniTripper.services.AuthService;
import com.example.AniTripper.views.AccountProfileView;

import java.security.Principal;

@Controller
@RequestMapping("/accounts")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("accountRegistrationDto")
    public AccountRegistrationDto initForm() {
        return new AccountRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid AccountRegistrationDto accountRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("accountRegistrationDto", accountRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.accountRegistrationDto", bindingResult);

            return "redirect:/accounts/register";
        }

        this.authService.register(accountRegistrationDto);

        return "redirect:/accounts/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/accounts/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        Account account = authService.getAccount(username);

        AccountProfileView userProfileView = new AccountProfileView(
                username,
                account.getEmail(),
                account.getFullName(),
                account.getAge()
        );

        model.addAttribute("account", userProfileView);

        return "profile";
    }
}
