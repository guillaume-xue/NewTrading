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

}
