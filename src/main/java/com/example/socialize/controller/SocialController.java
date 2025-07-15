package com.example.socialize.controller;

import com.example.socialize.Constants;
import com.example.socialize.entity.GroupChat;
import com.example.socialize.entity.User;
import com.example.socialize.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/create")
public class SocialController {
    @Autowired
    SocialService socialService;
    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody GroupChat groupChat){
        socialService.createGroup(groupChat);
        return ResponseEntity.ok(Constants.CREATED_GROUP_MSG);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(socialService.getUsers());
    }

    @GetMapping("/allMembers")
    public ResponseEntity<List<User>> getMembers(@RequestParam String user1){
        return ResponseEntity.ok(socialService.getMembers(user1));
    }

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam String username1,@RequestParam String username2){
        return ResponseEntity.ok(socialService.followUser(username1,username2));
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam String username1,@RequestParam String username2){
        return ResponseEntity.ok(socialService.unfollowUser(username1,username2));
    }
}
