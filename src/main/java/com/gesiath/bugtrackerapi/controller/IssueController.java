package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.dto.issue.*;
import com.gesiath.bugtrackerapi.service.IssueService;
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
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping
    public ResponseEntity<Page<IssueSummaryResponse>> getAll(
            @PageableDefault(size = 10, sort = "title") Pageable pageable){

        return ResponseEntity.ok(issueService.getAll(pageable));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getById(@PathVariable UUID id){

        return ResponseEntity.ok(issueService.getById(id));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER') or hasRole('REPORTER')")
    @PostMapping
    public ResponseEntity<IssueResponse> create(
            @Valid  @RequestBody IssueCreateRequest request){

        IssueResponse response = issueService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<IssueResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody IssueUpdateRequest request){

        return ResponseEntity.ok(issueService.update(id, request));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        issueService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<IssueSummaryResponse>> getByProject(
            @PathVariable UUID projectId,
            @PageableDefault(size = 10, sort = "title") Pageable pageable){

        return ResponseEntity.ok(issueService.getByProject(projectId, pageable));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<IssueResponse> patchStatus(
            @PathVariable UUID id,
            @Valid @RequestBody IssueUpdateStatusRequest request){

        return ResponseEntity.ok(issueService.patchStatus(id, request));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/assign/{developerId}")
    public ResponseEntity<IssueResponse> assignDeveloper(
            @PathVariable UUID id,
            @PathVariable UUID developerId){

        return ResponseEntity.ok(issueService.assignDeveloper(id, developerId));

    }

}
