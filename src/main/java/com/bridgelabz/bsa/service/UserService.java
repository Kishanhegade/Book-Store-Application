package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.dto.*;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.mapper.UserMapper;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User user = userMapper.mapToUser(registrationRequest);
        user = userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->
                new UsernameNotFoundException("username not found"));

        String token = jwtUtils.generateTokenFromUsername(user);
        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setJwtToken(token);
        return response;
    }

    public UserResponse findUserById(long userId) {
        return userRepository.findById(userId)
                .map(user -> userMapper.mapToUserResponse(user))
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user"));
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user->userMapper.mapToUserResponse(user)).toList();
    }

    public UserResponse updateUserById(long userId, UserRequest userRequest) {
        return userRepository.findById(userId)
                .map(user ->{
                    user = userMapper.mapToUser(userRequest,user);
                    user.setUpdatedDate(LocalDate.now());
                    userRepository.save(user);
                    return userMapper.mapToUserResponse(user);
                }).orElseThrow(() -> new UserNotFoundByIdException("Failed to update user"));
    }

    public UserResponse deleteUserById(long userId) {

        return userRepository.findById(userId)
                .map(user ->{
                    userRepository.deleteById(userId);
                    return userMapper.mapToUserResponse(user);
                }).orElseThrow(() -> new UserNotFoundByIdException("Failed to update user"));
    }
}
