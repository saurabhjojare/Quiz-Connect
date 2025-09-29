package com.galaxon.Quiz.Connect.service.interfaces;

import com.galaxon.Quiz.Connect.dto.request.UserRequest;
import com.galaxon.Quiz.Connect.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    boolean deleteUserById (Long userId);
    UserResponse updateUserById(UserRequest userRequest);
}
