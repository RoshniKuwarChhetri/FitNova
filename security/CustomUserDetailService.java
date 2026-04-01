package com.project.fitnova.security;

import com.project.fitnova.model.User;
import com.project.fitnova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())   // email used as username
                .password(user.getPassword())
                .roles(user.getRole().name()) // e.g. ADMIN / USER
                .build();
    }
}