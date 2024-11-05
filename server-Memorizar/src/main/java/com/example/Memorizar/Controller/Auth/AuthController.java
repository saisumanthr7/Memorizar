package com.example.Memorizar.Controller.Auth;

import com.example.Memorizar.DTO.Auth.AuthRequest;
import com.example.Memorizar.DTO.Auth.AuthResponse;
import com.example.Memorizar.DTO.Auth.SignupRequest;
import com.example.Memorizar.Service.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Memorizar/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest){
        AuthResponse authResponse = authService.signup(signupRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }
}