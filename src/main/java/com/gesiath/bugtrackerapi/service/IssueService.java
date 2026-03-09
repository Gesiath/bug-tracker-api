package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.issue.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IssueService {

    Page<IssueSummaryResponse> getAll(Pageable pageable);
    IssueResponse getById(UUID id);
    IssueResponse create(IssueCreateRequest dto);
    IssueResponse update(UUID id, IssueUpdateRequest dto);
    void delete(UUID id);
    Page<IssueSummaryResponse> getByProject(UUID projectId, Pageable pageable);
    IssueResponse patchStatus(UUID id, IssueUpdateStatusRequest dto);
    IssueResponse assignDeveloper(UUID id, UUID developerId);

}
