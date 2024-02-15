package com.api.mappers;


import com.api.dtos.UserDto;
import com.api.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDto userToUserDto(UserEntity user);
    List<UserDto> usersToUsersDto(List<UserEntity> users);
}
