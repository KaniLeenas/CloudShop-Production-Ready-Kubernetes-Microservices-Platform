package com.cloudshop.auth_service.controller;

import com.cloudshop.auth_service.dto.LoginRequest;
import com.cloudshop.auth_service.dto.RegisterRequest;
import com.cloudshop.auth_service.entity.User;
import com.cloudshop.auth_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
        @RequestBody LoginRequest request) {

    User user = authService.login(request);

    return ResponseEntity.ok(user);
}
} 