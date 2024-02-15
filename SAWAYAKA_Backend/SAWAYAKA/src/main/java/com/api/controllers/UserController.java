package com.api.controllers;

import com.api.dtos.UserDto;
import com.api.dtos.UserUpdateDto;
import com.api.interfaces.IUserService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
@SecurityRequirement(name = "bearer-jwt")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get a list of all users", description = "This endpoint returns a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users"),
    })
    public ResponseEntity<List<UserDto>> GetUsers(){
        List<UserDto> usersDto = userService.getUsers();

        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id", description = "This endpoint returns a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404",description = "UserEntity not found"),
    })
    public ResponseEntity<UserDto> GetUser(@PathVariable("id") Long id){
        UserDto userDto = userService.getUser(id);

        if(userDto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/current")
    @Operation(summary = "Get logged user", description = "This endpoint returns a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404",description = "UserEntity not found"),
    })
    public ResponseEntity<UserDto> GetCurrentUser(){
        UserDto userDto = userService.getCurrentUser();

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/current/update")
    @Operation(summary = "Update user", description = "This endpoint allows you to update your user data."+
            "Ensure the following criteria are met:\n " +
            "- Name must have a minimum length of 3 characters, maximum of 50.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Username not exist")
    })
    public ResponseEntity<?> updateCurrentUser(@Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        UserDto userDto= userService.updateCurrentUser(userUpdateDto);

        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/current/delete")
    @Operation(summary = "Delete user", description = "This endpoint allows you to delete your user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted")
    })
    public ResponseEntity<?> deleteCurrentUser(){

        userService.deleteCurrentUser();

        return ResponseEntity.noContent().build();
    }

}
