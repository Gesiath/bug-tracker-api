package com.gesiath.bugtrackerapi.service.impl;

import com.gesiath.bugtrackerapi.dto.issue.*;
import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.entity.Project;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.exception.CustomDataNotFoundException;
import com.gesiath.bugtrackerapi.mapper.IssueMapper;
import com.gesiath.bugtrackerapi.repository.IssueRepository;
import com.gesiath.bugtrackerapi.repository.ProjectRepository;
import com.gesiath.bugtrackerapi.repository.UserRepository;
import com.gesiath.bugtrackerapi.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Page<IssueSummaryResponse> getAll(Pageable pageable) {

        return issueRepository.findAll(pageable)
                .map(IssueMapper::toSummary);

    }

    @Override
    public IssueResponse getById(UUID id) {

        Issue issue = findIssue(id);

        return IssueMapper.toResponse(issue);

    }

    @Override
    public IssueResponse create(IssueCreateRequest dto) {

        Issue issue = IssueMapper.toEntity(dto);

        Project project = findProject(dto.getProjectId());

        User reporter = getCurrentUser();

        issue.setProject(project);

        issue.setUserReporter(reporter);

        issueRepository.save(issue);

        return IssueMapper.toResponse(issue);

    }

    @Override
    public IssueResponse update(UUID id, IssueUpdateRequest dto) {

        Issue issue = findIssue(id);

        IssueMapper.updateEntity(issue, dto);

        issueRepository.save(issue);

        return IssueMapper.toResponse(issue);

    }

    @Override
    public IssueResponse patchStatus(UUID id, IssueUpdateStatusRequest dto) {

        Issue issue = findIssue(id);

        issue.setIssueStatus(dto.getIssueStatus());

        issueRepository.save(issue);

        return IssueMapper.toResponse(issue);

    }

    @Override
    public IssueResponse assignDeveloper(UUID id, UUID developerId) {

        Issue issue = findIssue(id);

        User user = findUser(developerId);

        if (user.getRole() != UserRole.DEVELOPER){

            throw new IllegalStateException("User must be a developer to be assigned to an issue");

        }

        issue.setUserAssignee(user);

        issueRepository.save(issue);

        return IssueMapper.toResponse(issue);

    }

    @Override
    public void delete(UUID id) {

        Issue issue = findIssue(id);

        issueRepository.delete(issue);

    }

    private Issue findIssue(UUID id){

        return issueRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("Issue not found"));

    }

    private User findUser(UUID id){

        return userRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("User not found"));

    }

    private Project findProject(UUID id){

        return projectRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("Project not found"));

    }

    private User getCurrentUser(){

        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

    }

}
