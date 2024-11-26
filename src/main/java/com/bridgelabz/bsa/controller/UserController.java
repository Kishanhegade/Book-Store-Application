package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

}
