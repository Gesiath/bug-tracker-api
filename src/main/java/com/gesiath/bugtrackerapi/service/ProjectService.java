package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectCreateRequest;
import com.gesiath.bugtrackerapi.dto.project.ProjectResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectStatsResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    Page<ProjectResponse> getAll(Pageable pageable);
    Page<IssueSummaryResponse> getProjectIssues(UUID projectId, Pageable pageable);
    ProjectResponse getById(UUID id);
    ProjectResponse create(ProjectCreateRequest dto);
    ProjectResponse update(UUID id, ProjectUpdateRequest dto);
    void delete(UUID id);
    ProjectStatsResponse getStats(UUID projectId);

}
