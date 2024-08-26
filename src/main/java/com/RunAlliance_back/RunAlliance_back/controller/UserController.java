package com.RunAlliance_back.RunAlliance_back.controller;

import com.RunAlliance_back.RunAlliance_back.dto.UserRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserRegistrationDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserRegistrationDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserRegistrationDTO createUser(@RequestBody UserRegistrationDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public UserRegistrationDTO updateUser(@PathVariable Long userId, @RequestBody UserRegistrationDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
