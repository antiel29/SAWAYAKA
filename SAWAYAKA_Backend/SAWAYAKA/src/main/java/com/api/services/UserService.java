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
    public Boolean isUsernameTaken(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity actualUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void registerUser(UserRegisterDto user){
        UserEntity newUser = new UserEntity(user.getUsername(),passwordEncoder.encode(user.getPassword()));

        roleService.assignUserRoles(newUser,roleService.getUserRole());

        userRepository.save(newUser);
    }


    @Override
    public void updateCurrentUser(UserUpdateDto userUpdateDto) {
        UserEntity user = actualUser();
        user.setName(userUpdateDto.getName());

        userRepository.save(user);
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



    public UserEntity createNamedUser(String username, String password, String name, Role... roles) {
        UserEntity user = new UserEntity(username, password, name);
        roleService.assignUserRoles(user, roles);
        return user;
    }
    public UserEntity createAnonymusUser(String username, String password, Role... roles) {
        UserEntity user = new UserEntity(username, password);
        roleService.assignUserRoles(user, roles);
        return user;
    }


}
