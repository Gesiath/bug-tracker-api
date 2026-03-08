package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.comment.CommentCreateRequest;
import com.gesiath.bugtrackerapi.dto.comment.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {

    Page<CommentResponse> getByIssue(UUID issueId, Pageable pageable);
    CommentResponse create(CommentCreateRequest dto);
    void delete(UUID id);

}
