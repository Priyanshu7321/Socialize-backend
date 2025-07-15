package com.example.socialize.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "groups")
@Getter
@Setter
public class GroupChat {
    @Id
    private String id;

    private String groupName;
    private List<String> memberIds;
    private String creator;
}

