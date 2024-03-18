package com.example.bookstoreweb.service;

import com.example.bookstoreweb.dto.UserDto;
import com.example.bookstoreweb.mapper.RoleMapper;
import com.example.bookstoreweb.mapper.UserMapper;
import com.example.bookstoreweb.model.RegistrationRequest;
import com.example.bookstoreweb.model.User;
import com.example.bookstoreweb.repository.RoleRepository;
import com.example.bookstoreweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public UserDto registerUser(RegistrationRequest registrationRequest) {
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword())
                .emailAddress(registrationRequest.getEmailAddress())
                .role((roleRepository.findByRole("USER")))
                .build();

        if(!checkEmail(registrationRequest.getEmailAddress())) {
            return this.createUser(user);
        }
        return null;
    }

    public UserDto getLoginUser() {
        return userMapper.userEntityToDto(userRepository.findLoginUser().orElse(null));
    }

    public UserDto getUserById(Integer id) {
        return userMapper.userEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> getAllUsers() {
        return userMapper.userListEntityToDto(userRepository.findAll());
    }

    public List<UserDto> getAllRegularUsers() {
        return userMapper.regularUserListEntityToDto(userRepository.findAll());
    }

    public UserDto createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public UserDto updateUser(User user) {
        return userMapper.userEntityToDto(userRepository.save(user));
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
