package com.gym.GymTonic.controller;

import com.gym.GymTonic.payload.AuthRequest;
import com.gym.GymTonic.payload.AuthResponse;
import com.gym.GymTonic.payload.RegisterRequest;
import com.gym.GymTonic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse registerResp = authService.register(request);
        if (registerResp.getToken() == null){
            return new ResponseEntity<AuthResponse>(registerResp, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<AuthResponse>(registerResp, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return new ResponseEntity<AuthResponse>(authService.login(request), HttpStatus.OK);
    }
}
