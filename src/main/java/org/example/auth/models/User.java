package org.example.auth.models;

import lombok.Data;

@Data
public class User {
    Long id;
    String username;
    String password;
    String email;
    String avatarUrl;
    String about;
    String name;
    String surname;
    String hash;
    boolean isAdmin;
}
