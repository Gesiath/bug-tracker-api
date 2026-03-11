package com.gesiath.bugtrackerapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gesiath.bugtrackerapi.dto.auth.AuthResponse;
import com.gesiath.bugtrackerapi.dto.auth.LoginRequest;
import com.gesiath.bugtrackerapi.security.JwtAuthenticationFilter;
import com.gesiath.bugtrackerapi.security.JwtService;
import com.gesiath.bugtrackerapi.security.RateLimiterFilter;
import com.gesiath.bugtrackerapi.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private RateLimiterFilter rateLimiterFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldLoginSuccessfully() throws Exception{

        LoginRequest request = LoginRequest.builder()
                .email("test@test.com")
                .password("password")
                .build();

        AuthResponse response = AuthResponse.builder()
                .token("TaIqeifCY497Jd48hxgmgd48m3m03WgWwbgBxggTO+c=")//es un jwt random
                .build();

        when(authService.login(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

}
