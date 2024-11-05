package com.example.Memorizar.Service.Auth;
import com.example.Memorizar.DTO.Auth.AuthRequest;
import com.example.Memorizar.DTO.Auth.AuthResponse;
import com.example.Memorizar.DTO.Auth.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest signupRequest);
    AuthResponse login(AuthRequest authRequest);

    boolean userEmailExists(String email);
}