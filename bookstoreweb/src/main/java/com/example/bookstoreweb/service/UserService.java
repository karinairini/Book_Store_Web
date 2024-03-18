package com.example.bookstoreweb.service;

import com.example.bookstoreweb.dto.UserDto;
import com.example.bookstoreweb.model.RegistrationRequest;
import com.example.bookstoreweb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    List<UserDto> getAllRegularUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);

    Optional<User> findByUsername(String username);
}
