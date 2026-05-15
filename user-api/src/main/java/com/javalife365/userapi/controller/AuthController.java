package com.javalife365.userapi.controller;

import com.javalife365.userapi.dto.AuthRequest;
import com.javalife365.userapi.dto.RegisterRequest;
import com.javalife365.userapi.entity.User;
import com.javalife365.userapi.repository.UserRepository;
import com.javalife365.userapi.security.JwtService;
import com.javalife365.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201)
                .body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid Credentials!");
        }

        String token = jwtService.generateToken(request.getEmail());
        return ResponseEntity.status(200)
                .body(Map.of(
                                "token", token,
                                "role", user.getRole(),
                                "name", user.getLastName() + ", " + user.getFirstName()
                        )
                );
    }


}
