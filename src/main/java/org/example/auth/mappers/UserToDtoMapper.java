package org.example.auth.mappers;

import org.example.auth.models.User;
import org.example.auth.models.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserToDtoMapper {
    User AddUserRequestToUser(UserRequest userRequest);
}
