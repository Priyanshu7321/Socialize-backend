package com.example.socialize.service;

import com.example.socialize.entity.Message;
import com.example.socialize.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessengerService {

    @Autowired
    private MessageRepository messageRepo;

    public void sendMessageToUser(String senderId, String receiverId, String content) {
        Message msg = new Message();
        msg.setSenderId(senderId);
        msg.setReceiverId(receiverId);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());
        messageRepo.save(msg);
    }

    public void sendMessageToGroup(String senderId, String groupId, String content) {
        Message msg = new Message();
        msg.setSenderId(senderId);
        msg.setGroupId(groupId);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());
        messageRepo.save(msg);
    }

    public List<Message> getChatBetweenUsers(String user1, String user2) {
        List<Message> chat = messageRepo.findBySenderIdAndReceiverId(user1, user2);
        chat.addAll(messageRepo.findBySenderIdAndReceiverId(user2, user1));
        return chat;
    }

    public List<Message> getGroupChat(String groupId) {
        return messageRepo.findByGroupId(groupId);
    }
}

