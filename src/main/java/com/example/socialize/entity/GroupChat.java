package com.example.socialize.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "groups")
public class GroupChat {
    @Id
    private String id;

    private String groupName;

    private List<String> memberIds;
}

