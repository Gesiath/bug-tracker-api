package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.auth.AuthResponse;
import com.gesiath.bugtrackerapi.dto.auth.LoginRequest;
import com.gesiath.bugtrackerapi.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
