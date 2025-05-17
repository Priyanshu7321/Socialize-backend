package com.example.socialize.repository;

import com.example.socialize.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.Repository;

import java.util.Optional;
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
