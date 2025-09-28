package com.galaxon.Quiz.Connect.service.implementation;

import com.galaxon.Quiz.Connect.dto.request.UserRequest;
import com.galaxon.Quiz.Connect.dto.response.UserResponse;
import com.galaxon.Quiz.Connect.model.Users;
import com.galaxon.Quiz.Connect.repository.UserRepository;
import com.galaxon.Quiz.Connect.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserResponse createUser(UserRequest request) {
        try {
            Users users = new Users();
            users.setFirstName(request.getFirstName());
            users.setLastName(request.getLastName());
            users.setEmail(request.getEmail());
            users.setPhone(request.getPhone());
            users.setPassword(request.getPassword());

            Users savedUser = userRepository.save(users);

            // Map entity to response
            UserResponse response = new UserResponse();
            response.setId(savedUser.getId());
            response.setFirstName(savedUser.getFirstName());
            response.setLastName(savedUser.getLastName());
            response.setEmail(savedUser.getEmail());
            response.setPhone(savedUser.getPhone());

            return response;

        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return null;
        }
    }

}
