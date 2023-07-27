package org.example.auth.dao;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByUsernameContaining(String username);
    boolean existsByEmailContaining(String email);
    UserEntity findByUsername(String username);
}