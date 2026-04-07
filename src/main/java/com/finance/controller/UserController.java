package com.finance.controller;

import com.finance.dto.request.UserRequest;
import com.finance.dto.response.ApiResponse;
import com.finance.entity.User;
import com.finance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            User user = userService.createUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "User created successfully", user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        try {
            User user = userService.updateUser(id, userRequest);
            return ResponseEntity.ok(new ApiResponse(true, "User updated successfully", user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateUserStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            userService.updateUserStatus(id, User.Status.valueOf(status));
            return ResponseEntity.ok(new ApiResponse(true, "User status updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
}