package com.iaparatodos.backend.services;

import com.iaparatodos.backend.entities.User;
import com.iaparatodos.backend.jwt.JwtService;
import com.iaparatodos.backend.repositories.UserRepository;
import com.iaparatodos.backend.requests.LoginRequest;
import com.iaparatodos.backend.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + request.getEmail()));

        String token = jwtService.generateToken(userService.loadUserByUsername(request.getEmail()));

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }
}