package com.example.socialize.controller;

import com.example.socialize.entity.Post;
import com.example.socialize.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class PostController {
    @Autowired
    PostService postService;
    public String UPLOAD_DIR = "uploads/";
    @Async
    @PostMapping("upload/post")
    public CompletableFuture<ResponseEntity<Map<String,String>>> mediaupload(@RequestParam("file")MultipartFile file,@RequestBody Post post){
        Map<String,String> response = new HashMap<>();
        if(file.isEmpty()){
            response.put("message","empty file");
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response));
        }
        try{
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save file to folder
            String originalFileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(originalFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return file details
            response.put("fileName", originalFileName);
            response.put("size", String.valueOf(file.getSize()));
            response.put("path", filePath.toAbsolutePath().toString());
            response.put("message", "File uploaded successfully");

            postService.savePost(post);
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(response));

        } catch (IOException e) {
            response.put("message", "File upload failed");
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
        }
    }
}
