package org.example.auth.services;

import lombok.RequiredArgsConstructor;
import org.example.auth.dao.UserEntity;
import org.example.auth.dao.UserRepository;
import org.example.auth.exceptions.LoginException;
import org.example.auth.exceptions.RegistrationException;
import org.example.auth.mappers.UserToEntityMapper;
import org.example.auth.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultRegistrationService implements RegistrationService {
    private final UserRepository userRepository;

    private final UserToEntityMapper mapper;

    @Override
    public void register(User user) {
        if(userRepository.existsByUsernameContaining(user.getUsername()))
            throw new RegistrationException(
                    "Client with username: " + user.getUsername() + " already registered");
        if(userRepository.existsByEmailContaining(user.getEmail()))
            throw new RegistrationException(
                    "Client with email: " + user.getEmail() + " already registered");

        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setHash(hash);

        UserEntity userEntity = mapper.userToUserEntity(user);
        userRepository.save(userEntity);

    }

    @Override
    public void checkCredentials(String username, String password) {
        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userRepository
                .findByUsername(username));
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with username: " + username + " not found");

        UserEntity userEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(password, userEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}