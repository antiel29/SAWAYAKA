package com.api.services;

import com.api.dtos.UserDto;
import com.api.dtos.UserRegisterDto;
import com.api.dtos.UserUpdateDto;
import com.api.interfaces.IRoleService;
import com.api.interfaces.IUserService;
import com.api.mappers.IUserMapper;
import com.api.models.Role;
import com.api.models.UserEntity;
import com.api.repository.IUserRepository;
import com.api.security.CustomUserDetailsService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserMapper userMapper;
    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    UserService(IUserRepository userRepository, IUserMapper userMapper, IRoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.usersToUsersDto(users);
    }

    @Override
    public UserDto getUser(Long id){
        UserEntity user = userRepository.findById(id).orElse(null);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUser(String username){
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return userMapper.userToUserDto(user);
    }

    @Override
    public Boolean isUsernameTaken(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean isEmailTaken(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity actualUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDto registerUser(UserRegisterDto user){
        UserEntity newUser = new UserEntity(user.getUsername(),passwordEncoder.encode(user.getPassword()),user.getEmail());

        roleService.assignUserRoles(newUser,roleService.getUserRole());

        userRepository.save(newUser);

        return userMapper.userToUserDto(newUser);
    }


    @Override
    public UserDto updateCurrentUser(UserUpdateDto userUpdateDto) {
        UserEntity user = actualUser();
        user.setName(userUpdateDto.getName());

        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }
    @Override
    public void deleteCurrentUser() {
        UserEntity user = actualUser();

        roleService.disAssignUserRoles(user);
        userRepository.delete(user);

    }
    @Override
    public UserDto getCurrentUser() {
        UserEntity user = actualUser();
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserEntity createUser(String username, String password, String name, String email, Role... roles){
        UserEntity newUser = new UserEntity(username, passwordEncoder.encode(password), name,email);
        roleService.assignUserRoles(newUser, roles);
        userRepository.save(newUser);
        return newUser;
    }


}
