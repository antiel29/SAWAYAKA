package com.api.controllers;

import com.api.security.TokenBlackList;
import com.api.dtos.AuthResponseDto;
import com.api.dtos.LoginDto;
import com.api.dtos.UserDto;
import com.api.dtos.UserRegisterDto;
import com.api.interfaces.IUserService;
import com.api.security.JWTGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.utils.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private IUserService userService;
    private TokenBlackList tokenBlackList;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserService userService, TokenBlackList tokenBlackList, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenBlackList = tokenBlackList;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "This endpoint allows you to create a new user in the forum."+
            "To successfully register, ensure the following criteria are met: \n" +
            "- Username must have a minimum length of 3 characters,maximum of 50 and be unique.\n "+
            "- Password must have a minimum length of 5 characters,maximum of 50.\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "409", description = "Conflict")
    })
    public ResponseEntity<?> RegisterUser(@Valid @RequestBody UserRegisterDto provided, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse.formatErrors(bindingResult));

        if(userService.isUsernameTaken(provided.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");

        if(userService.isEmailTaken(provided.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");

        UserDto userDto= userService.registerUser(provided);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in the forum", description = "This endpoint allows you to log in the forum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully logged"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        if(!userService.isUsernameTaken(loginDto.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username don't exist");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        UserDto userDto = userService.getCurrentUser();

        return new ResponseEntity<>(new AuthResponseDto(token,userDto),HttpStatus.OK);
    }


    @GetMapping("/verify")
    @Operation(summary = "Verify the token", description = "This endpoint allows you to verify if your token is expired")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully logged"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<?> verifyToken(@RequestParam("token") String token){

        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token) && !tokenBlackList.isTokenInvalid(token)) {
            String username = jwtGenerator.getUsernameFromJWT(token);
            UserDto userDto = userService.getUser(username);

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/logout")
    @Operation(summary = "Logout from the forum", description = "This endpoint allows you to log out from the forum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully logged out"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<?> logout(@RequestParam("token") String token) {
        if (StringUtils.hasText(token)) {
            tokenBlackList.addToBlacklist(token);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
