package com.newtrading.api.controller;

import com.newtrading.api.model.User;
import com.newtrading.api.dto.UserRequest;
import com.newtrading.api.dto.UserResponse;
import com.newtrading.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPortfolioId());
        return ResponseEntity.ok(userResponse);
    }

    // Post new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setPortfolioId(userRequest.portfolioId());
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getPortfolioId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
