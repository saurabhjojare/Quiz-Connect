package com.galaxon.Quiz.Connect.wrapper;

import com.galaxon.Quiz.Connect.dto.request.UserRequest;
import com.galaxon.Quiz.Connect.dto.response.UserResponse;
import com.galaxon.Quiz.Connect.model.Users;

public class UserWrapper {

    public static Users toEntity(UserRequest request) {
        Users users = new Users();
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setPassword(request.getPassword());
        return users;
    }

    public static UserResponse toResponse(Users users) {
        UserResponse response = new UserResponse();
        response.setId(users.getId());
        response.setFirstName(users.getFirstName());
        response.setLastName(users.getLastName());
        response.setEmail(users.getEmail());
        response.setPhone(users.getPhone());
        return response;
    }
}
