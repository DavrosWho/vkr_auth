package org.example.auth.services;

public interface TokenService {
    String generateToken(String username);
}
