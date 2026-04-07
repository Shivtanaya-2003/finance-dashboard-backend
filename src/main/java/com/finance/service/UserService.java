package com.finance.service;

import com.finance.dto.request.UserRequest;
import com.finance.entity.User;
import com.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());  // Plain text password
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setStatus(User.Status.ACTIVE);
        
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    
    public User updateUser(Long id, UserRequest request) {
        User user = getUserById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(request.getPassword());  // Plain text
        }
        if (request.getRole() != null) {
            user.setRole(User.Role.valueOf(request.getRole()));
        }
        return userRepository.save(user);
    }
    
    public void updateUserStatus(Long id, User.Status status) {
        User user = getUserById(id);
        user.setStatus(status);
        userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}