package com.example.Memorizar.Service.Auth;

import com.example.Memorizar.Configuration.JWTService;
import com.example.Memorizar.DTO.Auth.AuthRequest;
import com.example.Memorizar.DTO.Auth.AuthResponse;
import com.example.Memorizar.DTO.Auth.SignupRequest;
import com.example.Memorizar.Entity.User;
import com.example.Memorizar.Enum.Role;
import com.example.Memorizar.Respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        if(userEmailExists(signupRequest.getEmail())){
            return AuthResponse.builder().errorMessage("Email already exists!").build();
        }else {
            var user = User.builder()
                    .firstName(signupRequest.getFirstName())
                    .lastName(signupRequest.getLastName())
                    .email(signupRequest.getEmail())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .role(Role.USER)
                    .build();
            var savedUser = userRepository.save(user);
            String jwtToken = jwtService.generateToken(savedUser);
            return AuthResponse.builder().accessToken(jwtToken).build();
        }
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username or Password entered");
        }

        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        Optional<User> optionalUser = userRepository.findByEmail(user.getUsername());
        String jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse();
        if(optionalUser.isPresent()){
            authResponse.setAccessToken(jwtToken);
            authResponse.setRole(optionalUser.get().getRole());
            authResponse.setUserId(optionalUser.get().getId());
        }
        return authResponse;
    }

    @Override
    public boolean userEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}