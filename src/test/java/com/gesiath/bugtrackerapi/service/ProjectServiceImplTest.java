package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.project.ProjectCreateRequest;
import com.gesiath.bugtrackerapi.dto.project.ProjectResponse;
import com.gesiath.bugtrackerapi.entity.Project;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.repository.ProjectRepository;
import com.gesiath.bugtrackerapi.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setup() {
        User currentUser = User.builder()
                .id(UUID.randomUUID())
                .name("Current User")
                .role(UserRole.ADMIN)
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(currentUser, null)
        );
    }

    @Test
    void shouldCreateProject(){

        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .name("Bug Tracker")
                .build();

        Project saved = Project.builder()
                .id(UUID.randomUUID())
                .name("Bug Tracker")
                .build();

        when(projectRepository.save(any())).thenReturn(saved);

        ProjectResponse response = projectService.create(request);

        assertEquals("Bug Tracker", response.getName());

    }

}
