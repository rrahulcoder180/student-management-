
package com.studentmanagement.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            AppUserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody AppUser user) {

        if (repository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message",
                            "Email already registered"));
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        user.setName(user.getName().trim());
        user.setEmail(user.getEmail().trim().toLowerCase());

        repository.save(user);

        return ResponseEntity.ok(
                Map.of("message", "Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AppUser user) {

        AppUser existing = repository
                .findByEmail(user.getEmail().trim().toLowerCase())
                .orElse(null);

        if (existing == null ||
                !passwordEncoder.matches(
                        user.getPassword(),
                        existing.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message",
                            "Invalid email or password"));
        }

        String token = jwtService.generateToken(existing.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "message", "Login successful"));
    }
}

