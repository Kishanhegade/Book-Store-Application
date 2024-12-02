package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.requestdto.*;
import com.bridgelabz.bsa.responsedto.LoginResponse;
import com.bridgelabz.bsa.responsedto.UserResponse;
import com.bridgelabz.bsa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register/users")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        UserResponse userResponse =  userService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login/users")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable long userId) {
        UserResponse userResponse =  userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> userResponses =  userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable long userId, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUserById(userId, userRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable long userId) {
        UserResponse userResponse = userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}


