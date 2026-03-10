package com.gesiath.bugtrackerapi.service.impl;

import com.gesiath.bugtrackerapi.dto.auth.AuthResponse;
import com.gesiath.bugtrackerapi.dto.auth.LoginRequest;
import com.gesiath.bugtrackerapi.dto.auth.RegisterRequest;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.mapper.UserMapper;
import com.gesiath.bugtrackerapi.repository.UserRepository;
import com.gesiath.bugtrackerapi.security.JwtService;
import com.gesiath.bugtrackerapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException("Email already in use");

        }

        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (userRepository.count() == 0) {
            user.setRole(UserRole.ADMIN);
        } else {
            user.setRole(UserRole.REPORTER);
        }

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

}
