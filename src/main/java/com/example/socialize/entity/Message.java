package com.example.socialize.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "message")
@Getter
@Setter
public class Message {
    @Id
    private String id;


    private String senderId;

    private String receiverId;

    private String groupId;

    private String content;

    private LocalDateTime timestamp;

}
