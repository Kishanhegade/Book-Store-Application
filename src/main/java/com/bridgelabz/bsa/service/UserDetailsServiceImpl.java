package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> {
                    return User.builder() //org.springframework.security.core.userdetails.User;
                            .username(user.getEmail())
                            .password(user.getPassword())
                            .roles(user.getRole())
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    }
}
