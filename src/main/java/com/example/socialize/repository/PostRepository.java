package com.example.socialize.repository;

import com.example.socialize.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String>{

}
