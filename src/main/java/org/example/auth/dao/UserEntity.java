package org.example.auth.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String about;

    private String name;

    private String surname;

    private String hash;

    @Column(name = "is_admin")
    private boolean isAdmin;
}