package com.example.bookstoreweb.controller;

import com.example.bookstoreweb.dto.UserDto;
import com.example.bookstoreweb.model.RegistrationRequest;
import com.example.bookstoreweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Home");

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");

        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("loginError", true);

        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value = "registrationSuccess", required = false) String success, Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());

        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes) {

        UserDto userDto = userService.registerUser(registrationRequest);
        if(userDto == null) {
            redirectAttributes.addAttribute("registrationSuccess", "Failed");
        } else {
            redirectAttributes.addAttribute("registrationSuccess", "Success");
        }

        return "redirect:/register";
    }
}
