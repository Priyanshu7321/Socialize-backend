package com.example.socialize.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.util.Pair;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Document(collection = "UsersInfo")
public class User {
    @Id
    String id;

    String username;
    String name;
    String email;
    String password;
    String age;
    String number;
    List<String> friendUserNames;
}
