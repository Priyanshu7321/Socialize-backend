package com.example.socialize.service;

import com.example.socialize.Constants;
import com.example.socialize.entity.GroupChat;
import com.example.socialize.entity.User;
import com.example.socialize.repository.GroupRepository;
import com.example.socialize.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocialService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    public void createGroup(GroupChat groupChat){
        groupRepository.save(groupChat);

    }

    public String followUser(String username1,String username2){
        User user1 = (userRepository.existsByUsername(username1)?userRepository.findByUsername(username1).get():null);

        User user2 = (userRepository.existsByUsername(username2)?userRepository.findByUsername(username2).get():null);

        user1.getFriendUserNames().put(username1,false);
        user2.getFriendUserNames().put(username2,true);
        return Constants.FOLLOWED_MSG;
    }
    public String unfollowUser(String username1,String username2){
        User user1 = (userRepository.existsByUsername(username1)?userRepository.findByUsername(username1).get():null);

        User user2 = (userRepository.existsByUsername(username2)?userRepository.findByUsername(username2).get():null);

        user1.getFriendUserNames().remove(username1,false);
        user2.getFriendUserNames().remove(username2,true);
        return Constants.FOLLOWED_MSG;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<User> getMembers(String username){
        List<User> userList = new ArrayList<>();
        Map<String, Boolean> members = userRepository.findByUsername(username).get().getFriendUserNames();
        for(Map.Entry<String,Boolean> entry:members.entrySet()){
            userList.add(userRepository.findByUsername(entry.getKey()).get());
        }
        return userList;
    }

}
