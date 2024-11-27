package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.dto.RegistrationRequest;
import com.bridgelabz.bsa.dto.UserRequest;
import com.bridgelabz.bsa.dto.UserResponse;
import com.bridgelabz.bsa.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class UserMapper {

    private BCryptPasswordEncoder encoder;

    public User mapToUser(UserRequest userRequest, User user) {
        user.setFname(userRequest.getFname());
        user.setLname(userRequest.getLname());
        user.setDob(userRequest.getDob());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        return user;
    }

    public User mapToUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFname(registrationRequest.getFname());
        user.setLname(registrationRequest.getLname());
        user.setEmail(registrationRequest.getEmail());
        user.setDob(registrationRequest.getDob());
        user.setRole(registrationRequest.getRole());
        user.setPassword(registrationRequest.getPassword());
        user.setRegisteredDate(LocalDate.now());
        return user;
    }
    public UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setFname(user.getFname());
        userResponse.setLname(user.getLname());
        userResponse.setEmail(user.getEmail());
        userResponse.setDob(user.getDob());
        userResponse.setRole(user.getRole());
        userResponse.setRegisteredDate(user.getRegisteredDate());
        userResponse.setUpdatedDate(user.getUpdatedDate());
        return userResponse;

    }
}
