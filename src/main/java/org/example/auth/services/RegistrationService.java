package org.example.auth.services;

import org.example.auth.models.User;

public interface RegistrationService {
    void register(User user);
    void checkCredentials(String username, String password);
}
