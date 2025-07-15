package com.example.socialize.repository;

import com.example.socialize.entity.GroupChat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<GroupChat,String> {
}
