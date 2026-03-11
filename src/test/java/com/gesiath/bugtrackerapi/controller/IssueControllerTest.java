package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.security.JwtAuthenticationFilter;
import com.gesiath.bugtrackerapi.security.JwtService;
import com.gesiath.bugtrackerapi.security.RateLimiterFilter;
import com.gesiath.bugtrackerapi.service.impl.IssueServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssueController.class)
@AutoConfigureMockMvc(addFilters = false)
public class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private RateLimiterFilter rateLimiterFilter;

    @MockitoBean
    IssueServiceImpl issueService;

    @Test
    void shouldReturnIssues() throws Exception{

        mockMvc.perform(get("/api/v1/issues"))
                .andExpect(status().isOk());

    }

}
