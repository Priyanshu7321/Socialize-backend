package com.example.socialize.controller;

import com.example.socialize.entity.User;
import com.example.socialize.service.LoginSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("start")
public class LoginSignUp {
    @Autowired
    LoginSignupService lsService;
    @PostMapping("signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody User user){
        return lsService.signup(user);
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user){
        return lsService.login(user);
    }

    @PatchMapping("update_record")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return lsService.update(user);
    }
}