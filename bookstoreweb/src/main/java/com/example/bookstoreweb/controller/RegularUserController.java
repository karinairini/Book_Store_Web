package com.example.bookstoreweb.controller;

import com.example.bookstoreweb.dto.UserDto;
import com.example.bookstoreweb.model.Book;
import com.example.bookstoreweb.model.RegistrationRequest;
import com.example.bookstoreweb.model.User;
import com.example.bookstoreweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegularUserController {
    private final UserService userService;

    @GetMapping("/regularUsers")
    public String getRegularUsers(Model model) {
        List<UserDto> userDtos = userService.getAllRegularUsers();
        model.addAttribute("title", "Regular Users");
        model.addAttribute("users", userDtos);
        return "regularUsers";
    }

    @GetMapping("/addUser")
    public String displayAddUserForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String processAddUserForm(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes) {
        UserDto userDto = userService.registerUser(registrationRequest);
        if(userDto == null) {
            redirectAttributes.addAttribute("registrationSuccess", "Failed");
        } else {
            redirectAttributes.addAttribute("registrationSuccess", "Success");
        }
        return "redirect:/regularUsers";
    }

    @GetMapping("/deleteUser")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("users", userService.getAllRegularUsers());
        return "deleteUser";
    }

    @PostMapping("/deleteUser")
    public String processDeleteUserForm(@RequestParam(name = "username", required = false) String username) {
        if (username != null) {
            User userToDelete = userService.findByUsername(username).get();
            if (userToDelete != null) {
                userService.deleteUser(userToDelete);
            }
        }
        return "redirect:/regularUsers";
    }
}
