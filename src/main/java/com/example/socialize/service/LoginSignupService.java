package com.example.socialize.service;

import com.example.socialize.entity.User;
import com.example.socialize.repository.UserRepository;
import com.example.socialize.tokenConfig.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginSignupService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    public ResponseEntity<Map<String, String>> login(User user) {
        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        if (user1.getPassword().equals(user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    public ResponseEntity<Map<String, String>> signup(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        userRepository.save(user);
        String token = jwtTokenUtil.generateToken(user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("message", "Signup successful");
        response.put("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<String> update(User user){
        userRepository.save(user);
        String message = "task successful";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
