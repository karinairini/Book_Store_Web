package com.example.bookstoreweb.controller;

import com.example.bookstoreweb.dto.UserDto;
import com.example.bookstoreweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
}
