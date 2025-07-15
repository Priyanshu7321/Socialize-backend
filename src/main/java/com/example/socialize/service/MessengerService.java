package com.example.socialize.service;

import com.example.socialize.entity.GroupChat;
import com.example.socialize.entity.Message;
import com.example.socialize.repository.GroupRepository;
import com.example.socialize.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MessengerService {

    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private GroupRepository groupRepository;

    @Async
    public CompletableFuture<Void> sendMessageToUser(String senderId, String receiverId, String content) {
        Message msg = new Message();
        msg.setSenderId(senderId);
        msg.setReceiverId(receiverId);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());
        messageRepo.save(msg);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> sendMessageToGroup(String senderId, String groupId, String content) {
        Message msg = new Message();
        msg.setSenderId(senderId);
        msg.setGroupId(groupId);
        msg.setContent(content);
        msg.setTimestamp(LocalDateTime.now());
        messageRepo.save(msg);
        return CompletableFuture.completedFuture(null);
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

