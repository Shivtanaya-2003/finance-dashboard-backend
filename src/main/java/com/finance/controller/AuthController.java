package com.finance.controller;

import com.finance.dto.request.LoginRequest;
import com.finance.dto.request.RegisterRequest;
import com.finance.dto.response.ApiResponse;
import com.finance.dto.response.AuthResponse;
import com.finance.entity.User;
import com.finance.dto.request.UserRequest;
import com.finance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.getUserByEmail(loginRequest.getEmail());
            
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse(false, "User not found"));
            }
            
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse(false, "Invalid password"));
            }
            
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid email or password"));
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setName(registerRequest.getName());
            userRequest.setEmail(registerRequest.getEmail());
            userRequest.setPassword(registerRequest.getPassword());
            userRequest.setRole("VIEWER");
            
            User user = userService.createUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "User registered successfully", user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
}