package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.dto.project.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    Page<ProjectResponse> getAll(
            ProjectFilterRequest filters,
            Pageable pageable);
    ProjectResponse getById(UUID id);
    ProjectResponse create(ProjectCreateRequest dto);
    ProjectResponse update(UUID id, ProjectUpdateRequest dto);
    void delete(UUID id);
    ProjectStatsResponse getStats(UUID projectId);
    Page<IssueSummaryResponse> getProjectIssues(UUID projectId, Pageable pageable);

}
