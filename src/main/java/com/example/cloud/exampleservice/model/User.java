package com.example.cloud.exampleservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "application_users")
@Getter
@Setter
public class User {

    private String id;
    private String name;
    private String password;
    private String address;
    private String role;
}
