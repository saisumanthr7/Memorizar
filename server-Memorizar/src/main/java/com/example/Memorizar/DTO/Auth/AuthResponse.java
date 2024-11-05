package com.example.Memorizar.DTO.Auth;

import com.example.Memorizar.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String accessToken;
    private Role role;
    private Long userId;
    private String errorMessage;

}