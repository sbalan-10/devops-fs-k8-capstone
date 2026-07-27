package com.devops.auth.service;

import com.devops.auth.dto.AuthResponse;
import com.devops.auth.dto.LoginRequest;
import com.devops.auth.dto.RegisterRequest;
import com.devops.auth.entity.User;
import com.devops.auth.repository.UserRepository;
import com.devops.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register a new user
     */
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(
                    null,
                    "Email already exists",
                    request.getEmail(),
                    null
            );
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "User Registered Successfully",
                user.getEmail(),
                user.getRole()
        );
    }

    /**
     * Login existing user
     */
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {

            return new AuthResponse(
                    null,
                    "User not found",
                    request.getEmail(),
                    null
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return new AuthResponse(
                    null,
                    "Invalid password",
                    request.getEmail(),
                    null
            );
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                "Login Successful",
                user.getEmail(),
                user.getRole()
        );
    }

    /**
     * Fetch user profile
     */
    public User getUser(String email) {

        return userRepository.findByEmail(email).orElse(null);

    }

}
