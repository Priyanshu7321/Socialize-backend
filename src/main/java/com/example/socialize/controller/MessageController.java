package com.example.socialize.controller;

import com.example.socialize.Constants;
import com.example.socialize.entity.GroupChat;
import com.example.socialize.entity.Message;
import com.example.socialize.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {

    @Autowired
    private MessengerService messengerService;


    @PostMapping("/send/user")
    public ResponseEntity<String> sendUserMsg(@RequestBody Message message) {
        messengerService.sendMessageToUser(message.getSenderId(), message.getReceiverId(), message.getContent());
        return ResponseEntity.ok(Constants.USER_MESSAGE_SENT);
    }

    @PostMapping("/send/group")
    public ResponseEntity<String> sendGroupMsg(@RequestBody Message message) {
        messengerService.sendMessageToGroup(message.getSenderId(), message.getGroupId(), message.getContent());
        return ResponseEntity.ok(Constants.GROUP_MESSAGE_SENT);
    }


    @GetMapping("/getUserChat")
    public ResponseEntity<List<Message>> getUserChat(@RequestParam String user1,
                                                     @RequestParam String user2) {
        return ResponseEntity.ok(messengerService.getChatBetweenUsers(user1, user2));
    }

    @GetMapping("/getGroupChat")
    public ResponseEntity<List<Message>> getGroupChat(@RequestParam String groupId) {
        return ResponseEntity.ok(messengerService.getGroupChat(groupId));
    }



//    @PostMapping("/setGroupChat")
//    public ResponseEntity<String> setGroupChat(@RequestParam )
}

