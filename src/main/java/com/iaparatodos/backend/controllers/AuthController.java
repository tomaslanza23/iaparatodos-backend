package com.iaparatodos.backend.controllers;

import com.iaparatodos.backend.entities.User;
import com.iaparatodos.backend.jwt.JwtService;
import com.iaparatodos.backend.repositories.UserRepository;
import com.iaparatodos.backend.requests.LoginRequest;
import com.iaparatodos.backend.responses.AuthResponse;
import com.iaparatodos.backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/setup-admin")
    public ResponseEntity<?> setupAdmin(@RequestBody LoginRequest request) {
        if (userRepository.count() > 0) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Ya existe un administrador. Operación abortada."));
        }

        User admin = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(admin);

        return ResponseEntity.ok(Map.of("message", "Admin creado: " + request.getEmail()));
    }
}