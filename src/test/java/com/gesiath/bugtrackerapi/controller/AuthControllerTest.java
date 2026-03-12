package com.gesiath.bugtrackerapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gesiath.bugtrackerapi.dto.auth.AuthResponse;
import com.gesiath.bugtrackerapi.dto.auth.LoginRequest;
import com.gesiath.bugtrackerapi.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        // Preparar request y response fake
        LoginRequest request = LoginRequest.builder()
                .email("test@test.com")
                .password("password")
                .build();

        AuthResponse response = AuthResponse.builder()
                .token("jwt-fake-token")
                .build();

        when(authService.login(any())).thenReturn(response);

        /*Llamada al metodo directamente*/
        ResponseEntity<AuthResponse> result = authController.login(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("jwt-fake-token", result.getBody().getToken());
    }
}