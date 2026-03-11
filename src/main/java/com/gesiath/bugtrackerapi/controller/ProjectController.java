package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.dto.project.*;
import com.gesiath.bugtrackerapi.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getAll(
            ProjectFilterRequest filters,
            @PageableDefault(size = 10, sort = "name") Pageable pageable){

        return ResponseEntity.ok(projectService.getAll(filters, pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable UUID id){

        return ResponseEntity.ok(projectService.getById(id));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectResponse> create(
            @Valid @RequestBody ProjectCreateRequest request){

        ProjectResponse response = projectService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProjectUpdateRequest request){

        return ResponseEntity.ok(projectService.update(id, request));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        projectService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping("/{id}/issues")
    public ResponseEntity<Page<IssueSummaryResponse>> getProjectIssues(
            @PathVariable UUID id,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable){

        return ResponseEntity.ok(projectService.getProjectIssues(id, pageable));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping("/{id}/stats")
    public ResponseEntity<ProjectStatsResponse> getStats(@PathVariable UUID id){

        return ResponseEntity.ok(projectService.getStats(id));

    }

}
