package com.project.fitnova.service;

import com.project.fitnova.dto.RegisterRequest;
import com.project.fitnova.dto.UserResponse;
import com.project.fitnova.model.User;
import com.project.fitnova.model.UserRole;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register User
    public UserResponse register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // ✅ Entity → DTO
    public UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}