package org.example.auth.mappers;

import org.example.auth.dao.UserEntity;
import org.example.auth.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserToEntityMapper {
    UserEntity userToUserEntity(User user);
}
