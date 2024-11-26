package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.dto.LoginRequest;
import com.bridgelabz.bsa.dto.UserRequest;
import com.bridgelabz.bsa.dto.UserResponse;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.mapper.UserMapper;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserResponse registerUser(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        user = userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    public void login(LoginRequest loginRequest) {
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
                    user = userMapper.mapToUser(userRequest);
                    userRepository.save(user);
                    return userMapper.mapToUserResponse(user);
                }).orElseThrow(() -> new UserNotFoundByIdException("Failer to update user"));
    }

    public UserResponse deleteUserById(long userId) {

        return userRepository.findById(userId)
                .map(user ->{
                    userRepository.deleteById(userId);
                    return userMapper.mapToUserResponse(user);
                }).orElseThrow(() -> new UserNotFoundByIdException("Failed to update user"));
    }
}
