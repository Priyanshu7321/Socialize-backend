package com.example.socialize.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class MediaController {
    public String UPLOAD_DIR = "uploads/";
    @PostMapping("upload/post")
    public ResponseEntity<Map<String,String>> mediaupload(@RequestParam("file")MultipartFile file){
        Map<String,String> response = new HashMap<>();
        response.put("message","file uploaded successfully");
        if(file.isEmpty()){
            response.put("message","empty file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IOException e) {
            response.put("message", "File upload failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }


}
