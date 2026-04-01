package com.project.fitnova.dto;

import com.project.fitnova.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message ="Email required")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message ="Passwoed required")
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;

}
