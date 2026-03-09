package com.gesiath.bugtrackerapi.service.impl;

import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectCreateRequest;
import com.gesiath.bugtrackerapi.dto.project.ProjectResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectStatsResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectUpdateRequest;
import com.gesiath.bugtrackerapi.entity.Project;
import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.exception.CustomDataNotFoundException;
import com.gesiath.bugtrackerapi.mapper.IssueMapper;
import com.gesiath.bugtrackerapi.mapper.ProjectMapper;
import com.gesiath.bugtrackerapi.repository.IssueRepository;
import com.gesiath.bugtrackerapi.repository.ProjectRepository;
import com.gesiath.bugtrackerapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;

    @Override
    public Page<ProjectResponse> getAll(Pageable pageable) {

        return projectRepository.findAll(pageable)
                .map(ProjectMapper::toResponse);

    }

    @Override
    public ProjectResponse getById(UUID id) {

        Project project = findProject(id);

        return ProjectMapper.toResponse(project);

    }

    @Override
    public ProjectResponse create(ProjectCreateRequest dto) {

        Project project = ProjectMapper.toEntity(dto);

        projectRepository.save(project);

        return ProjectMapper.toResponse(project);

    }

    @Override
    public ProjectResponse update(UUID id, ProjectUpdateRequest dto) {

        Project project = findProject(id);

        ProjectMapper.updateEntity(project, dto);

        projectRepository.save(project);

        return ProjectMapper.toResponse(project);

    }

    @Override
    public void delete(UUID id) {

        Project project = findProject(id);

        projectRepository.delete(project);

    }

    @Override
    public Page<IssueSummaryResponse> getProjectIssues(UUID projectId, Pageable pageable) {

        Project project = findProject(projectId);
        return issueRepository.findByProjectId(project.getId(), pageable)
                .map(IssueMapper::toSummary);

    }

    @Override
    public ProjectStatsResponse getStats(UUID projectId) {

        Project project = findProject(projectId);

        long total = issueRepository.countByProjectId(project.getId());
        long open = issueRepository.countByProjectIdAndIssueStatus(project.getId(), IssueStatus.OPEN);
        long inProgress = issueRepository.countByProjectIdAndIssueStatus(project.getId(), IssueStatus.IN_PROGRESS);
        long resolved = issueRepository.countByProjectIdAndIssueStatus(project.getId(), IssueStatus.RESOLVED);
        long closed = issueRepository.countByProjectIdAndIssueStatus(project.getId(), IssueStatus.CLOSED);

        return ProjectStatsResponse.builder()
                .totalIssues(total)
                .openIssues(open)
                .inProgressIssues(inProgress)
                .resolvedIssues(resolved)
                .closedIssues(closed)
                .build();

    }

    private Project findProject(UUID id){

        return projectRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("Project not found"));

    }

}
