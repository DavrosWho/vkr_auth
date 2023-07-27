package org.example.auth.models;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
}
