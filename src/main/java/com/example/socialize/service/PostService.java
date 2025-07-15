package com.example.socialize.service;

import com.example.socialize.entity.Post;
import com.example.socialize.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService{
    @Autowired
    PostRepository postRepository;
    public void savePost(Post post){
        postRepository.save(post);
    }
}
