package com.api.interfaces;

import com.api.dtos.UserDto;
import com.api.dtos.UserRegisterDto;
import com.api.dtos.UserUpdateDto;
import com.api.models.Role;
import com.api.models.UserEntity;
import jakarta.validation.constraints.Email;

import java.util.List;

public interface IUserService  {

     List<UserDto> getUsers();
     UserDto getUser(Long id);

    UserDto getUser(String Username);

     Boolean isUsernameTaken(String username);

    Boolean isEmailTaken(String email);

     UserEntity actualUser();


     UserDto registerUser(UserRegisterDto user);

     UserEntity createUser(String username, String password, String name, String email ,Role... roles);

    UserDto updateCurrentUser(UserUpdateDto userUpdateDto);

     void deleteCurrentUser();

    UserDto getCurrentUser();
}
