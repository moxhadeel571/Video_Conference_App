package com.videoc.example.videoc.DAO;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString
@Document(collection = "users")
public class User {

    private String username;
    private String email;
    private String password;
    private String status;
}
