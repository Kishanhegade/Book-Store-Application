package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.dto.LoginRequest;
import com.bridgelabz.bsa.dto.UserRequest;
import com.bridgelabz.bsa.dto.UserResponse;
import com.bridgelabz.bsa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse =  userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    public String login(@RequestBody LoginRequest loginRequest) {
         userService.login(loginRequest);
    }

    public ResponseEntity<UserResponse> findUserById(@PathVariable long userId) {
        UserResponse userResponse =  userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> userResponses =  userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    public ResponseEntity<UserResponse> updateUserById(@PathVariable long userId) {
        UserResponse userResponse = userService.updateUserById(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }

    public ResponseEntity<UserResponse> deleteUserById(@PathVariable long userId) {
        UserResponse userResponse = userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}


