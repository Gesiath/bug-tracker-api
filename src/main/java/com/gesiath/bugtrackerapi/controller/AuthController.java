package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.dto.auth.AuthResponse;
import com.gesiath.bugtrackerapi.dto.auth.LoginRequest;
import com.gesiath.bugtrackerapi.dto.auth.RegisterRequest;
import com.gesiath.bugtrackerapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request){

        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/login")
    private ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.login(request));

    }

}
