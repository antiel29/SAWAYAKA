package com.api.interfaces;

import com.api.dtos.UserDto;
import com.api.dtos.UserRegisterDto;
import com.api.dtos.UserUpdateDto;
import com.api.models.Role;
import com.api.models.UserEntity;

import java.util.List;

public interface IUserService  {

     List<UserDto> getUsers();
     UserDto getUser(Long id);

     Boolean isUsernameTaken(String username);

     UserEntity actualUser();


     void registerUser(UserRegisterDto user);

     UserEntity createNamedUser(String username, String password, String name, Role... roles);
     UserEntity createAnonymusUser(String username, String password, Role... roles);

    void updateCurrentUser(UserUpdateDto userUpdateDto);

     void deleteCurrentUser();

    UserDto getCurrentUser();
}
