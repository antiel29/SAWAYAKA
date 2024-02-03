package com.api.controllers;

import com.api.dtos.AuthResponseDto;
import com.api.dtos.LoginDto;
import com.api.dtos.UserRegisterDto;
import com.api.interfaces.IUserService;
import com.api.security.JWTGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private IUserService userService;

    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserService userService, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "This endpoint allows you to create a new user in the forum."+
            "To successfully register, ensure the following criteria are met:\n " +
            "- Username must have a minimum length of 3 characters,maximum of 50 and be unique.\n "+
            "- Password must have a minimum length of 5 characters,maximum of 50.\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "409", description = "Conflict - Username already taken")
    })
    public ResponseEntity<?> RegisterUser(@Valid @RequestBody UserRegisterDto provided, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        if(userService.isUsernameTaken(provided.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Already taken");

        userService.registerUser(provided);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Log in the forum", description = "This endpoint allows you to log in the forum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully logged"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
    }
}
