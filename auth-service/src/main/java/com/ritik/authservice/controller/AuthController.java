package com.ritik.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ritik.authservice.dto.RegisterRequest;
import com.ritik.authservice.dto.LoginRequest;
import com.ritik.authservice.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Auth Service";
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
    	return authService.login(request);
    }
}