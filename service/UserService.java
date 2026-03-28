package com.project.fitnova.service;

import com.project.fitnova.dto.RegisterRequest;
import com.project.fitnova.dto.UserResponse;
import com.project.fitnova.model.User;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserResponse register(RegisterRequest request) {
        User user= User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getEmail())
                .password(request.getPassword())
                .build();



        User savedUser =userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        return response;


    }

}
