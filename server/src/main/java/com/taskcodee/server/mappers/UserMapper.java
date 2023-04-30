package com.taskcodee.server.mappers;

import com.taskcodee.server.dto.users.UserCreationDTO;
import com.taskcodee.server.dto.users.UserDTO;
import com.taskcodee.server.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    User mapToUserEntity(UserCreationDTO userCreationDTO);

    UserDTO mapToUserDTO(User user);
}
