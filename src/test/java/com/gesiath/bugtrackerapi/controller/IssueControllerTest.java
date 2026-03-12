package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.dto.issue.IssueFilterRequest;
import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.service.IssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IssueControllerTest {

    @InjectMocks
    private IssueController issueController;

    @Mock
    private IssueService issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnIssues() {
        // Preparar fake response
        IssueSummaryResponse fakeIssue = IssueSummaryResponse.builder()
                .id(UUID.randomUUID())
                .title("Fake Issue")
                .build();
        Page<IssueSummaryResponse> fakePage = new PageImpl<>(List.of(fakeIssue));

        // Mockear servicio
        when(issueService.getAll(any(IssueFilterRequest.class), any(Pageable.class)))
                .thenReturn(fakePage);

        // Llamada directa al metodo del controller
        ResponseEntity<Page<IssueSummaryResponse>> response =
                issueController.getAll(new IssueFilterRequest(), Pageable.unpaged());

        // Validar
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals("Fake Issue", response.getBody().getContent().get(0).getTitle());
    }
}
