package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.issue.IssueCreateRequest;
import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.entity.Project;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.enumerator.Priority;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.repository.IssueRepository;
import com.gesiath.bugtrackerapi.repository.ProjectRepository;
import com.gesiath.bugtrackerapi.repository.UserRepository;
import com.gesiath.bugtrackerapi.service.impl.IssueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setup(){

        User reporter = User.builder()
                .id(UUID.randomUUID())
                .name("Reporter")
                .role(UserRole.REPORTER)
                .build();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(reporter, null)
        );

    }

    @Test
    void shouldCreateIssue(){

        UUID projectId = UUID.randomUUID();

        IssueCreateRequest request = IssueCreateRequest.builder()
                .title("Bug title")
                .description("Bug description")
                .priority(Priority.HIGH)
                .projectId(projectId)
                .build();

        Project project = Project.builder()
                .id(projectId)
                .name("Test project")
                .build();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(issueRepository.save(any())).thenReturn(new Issue());

        var response = issueService.create(request);

        assertEquals("Bug title", response.getTitle());

    }

}
