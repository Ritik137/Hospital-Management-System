package com.ritik.authservice.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ritik.authservice.dto.LoginRequest;
import com.ritik.authservice.dto.RegisterRequest;
import com.ritik.authservice.entity.User;
import com.ritik.authservice.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    Register.....
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";
    }
    
//    Login....
    public String login(LoginRequest request) {
    	Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
    	if(optionalUser.isEmpty()) {
    		return "User not found";
    	}
    	
    	User user = optionalUser.get();
    	
    	if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    		return "invalid password";
    	}
    	return "Login Successful";
    }
}