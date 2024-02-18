package com.api.mappers;

import com.api.dtos.UserDto;
import com.api.models.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-17T22:07:48-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserDto userToUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setEmail( user.getEmail() );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setName( user.getName() );

        return userDto;
    }

    @Override
    public List<UserDto> usersToUsersDto(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( userToUserDto( userEntity ) );
        }

        return list;
    }
}
