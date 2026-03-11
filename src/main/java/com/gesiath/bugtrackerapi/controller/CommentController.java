package com.gesiath.bugtrackerapi.controller;

import com.gesiath.bugtrackerapi.dto.comment.CommentCreateRequest;
import com.gesiath.bugtrackerapi.dto.comment.CommentResponse;
import com.gesiath.bugtrackerapi.service.CommentService;
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
@RequestMapping("/api/v1/issues/{issueId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getByIssue(
            @PathVariable UUID issueId,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable){

        return ResponseEntity.ok(commentService.getByIssue(issueId, pageable));

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER') or hasRole('REPORTER')")
    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable UUID issueId,
            @Valid @RequestBody CommentCreateRequest request){

        CommentResponse response = commentService.create(issueId, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        commentService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
