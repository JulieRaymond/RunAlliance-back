package com.RunAlliance_back.RunAlliance_back.mapper;

import com.RunAlliance_back.RunAlliance_back.dto.UserRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "password", ignore = true)
        // Ne pas exposer le mot de passe
    UserRegistrationDTO userToUserRegistrationDTO(User user);

    @Mapping(target = "email", source = "userDTO.email")
    @Mapping(target = "password", source = "userDTO.password")
    User userRegistrationDTOToUser(UserRegistrationDTO userDTO);

    void updateUserFromDTO(UserRegistrationDTO userDTO, @MappingTarget User user);
}
