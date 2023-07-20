package com.gym.GymTonic.service;

import com.gym.GymTonic.model.mongo.UserModel;
import com.gym.GymTonic.payload.AuthRequest;
import com.gym.GymTonic.payload.AuthResponse;
import com.gym.GymTonic.payload.RegisterRequest;
import com.gym.GymTonic.repository.mongo.UserRepository;
import com.gym.GymTonic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public static UserModel getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserModel) authentication.getPrincipal();
        }
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse();
        }

        UserModel userModel = new UserModel(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(userModel);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtil.generateToken(userModel);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserModel userModel = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(userModel);
        return new AuthResponse(token);
    }
}
