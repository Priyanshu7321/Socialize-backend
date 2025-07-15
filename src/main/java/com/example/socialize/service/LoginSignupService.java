package com.example.socialize.service;

import com.example.socialize.Constants;
import com.example.socialize.entity.User;
import com.example.socialize.repository.UserRepository;
import com.example.socialize.tokenConfig.JwtTokenUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class LoginSignupService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    public ResponseEntity<Map<String, String>> login(User user) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        if (user1.getPassword().equals(user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            response.put("message","Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> signup(User user) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Signup successful");
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            response.put("message","User already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = jwtTokenUtil.generateToken(user.getUsername());
        response.put("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<Map<String,String>> update(User user){
        Map<String,String> response = new HashMap<>();
        String message = Constants.UPDATE_MSG;
        response.put("message",message);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
