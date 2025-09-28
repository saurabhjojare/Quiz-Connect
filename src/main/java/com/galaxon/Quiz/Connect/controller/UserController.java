package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.constants.Routes;
import com.galaxon.Quiz.Connect.dto.request.UserRequest;
import com.galaxon.Quiz.Connect.dto.response.UserResponse;
import com.galaxon.Quiz.Connect.service.implementation.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.BASE_API + Routes.User.BASE_USER)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}
