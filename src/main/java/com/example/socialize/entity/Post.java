package com.example.socialize.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "posts")
@Getter
@Setter
public class Post {
    @Id
    String id;
    String postFileName;
    String postFileSize;
    String postUploaderId;
    String postInfo;
    Map<String,List<String>> postComments;
    Map<String,List<String>> postReactions;
    Map<String,List<String>> postShares;
}
