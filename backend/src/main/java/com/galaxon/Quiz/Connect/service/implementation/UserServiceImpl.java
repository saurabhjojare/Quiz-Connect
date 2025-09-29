package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.request.UserRequest;
import com.galaxon.Quiz.Connect.dto.response.UserResponse;
import com.galaxon.Quiz.Connect.model.Users;
import com.galaxon.Quiz.Connect.repository.UserRepository;
import com.galaxon.Quiz.Connect.service.interfaces.UserService;
import com.galaxon.Quiz.Connect.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        try {
            Users users = UserWrapper.toEntity(userRequest);
            Users savedUser = userRepository.save(users);

            log.info("Created user with email: {}", savedUser.getEmail());

            return UserWrapper.toResponse(savedUser);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return null;
        }
    }

    // TODO Implement logic to delete a user by their ID in the database.
    @Override
    public boolean deleteUserById(Long userId) {
        return false;
    }

    // TODO Implement logic to update a user's details based on the request.
    @Override
    public UserResponse updateUserById(UserRequest request) {
        return null;
    }

}
