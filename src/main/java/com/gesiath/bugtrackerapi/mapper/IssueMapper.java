package com.gesiath.bugtrackerapi.mapper;

import com.gesiath.bugtrackerapi.dto.issue.IssueCreateRequest;
import com.gesiath.bugtrackerapi.dto.issue.IssueResponse;
import com.gesiath.bugtrackerapi.dto.issue.IssueSummaryResponse;
import com.gesiath.bugtrackerapi.dto.issue.IssueUpdateRequest;
import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.repository.projection.IssueSummaryProjection;

public class IssueMapper {

    private IssueMapper(){

    }

    public static IssueResponse toResponse(Issue issue){

        if (issue == null){

            return null;

        }

        return IssueResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .issueStatus(issue.getIssueStatus())
                .priority(issue.getPriority())
                .createdAt(issue.getCreatedAt())
                .updatedAt(issue.getUpdatedAt())
                .projectId(issue.getProject().getId())
                .projectName(issue.getProject().getName())
                .reporter(UserMapper.toSummary(issue.getUserReporter()))
                .assignee(
                        issue.getUserAssignee() != null
                                ? UserMapper.toSummary(issue.getUserAssignee())
                                : null
                )
                .build();

    }

    public static IssueSummaryResponse toSummary(Issue issue){

        if (issue == null){

            return null;

        }

        return IssueSummaryResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .issueStatus(issue.getIssueStatus())
                .priority(issue.getPriority())
                .build();

    }

    public static IssueSummaryResponse toProjected(IssueSummaryProjection issue){

        if (issue == null){

            return null;

        }

        return IssueSummaryResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .issueStatus(issue.getIssueStatus())
                .priority(issue.getPriority())
                .build();

    }

    public static Issue toEntity(IssueCreateRequest dto){

        if (dto == null){

            return null;

        }

        return Issue.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .build();

    }

    public static void updateEntity(Issue issue, IssueUpdateRequest dto){

        if (dto.getTitle() != null){

            issue.setTitle(dto.getTitle());

        }

        if (dto.getDescription() != null){

            issue.setDescription(dto.getDescription());

        }

        if (dto.getIssueStatus() != null){

            issue.setIssueStatus(dto.getIssueStatus());

        }

        if (dto.getPriority() != null){

            issue.setPriority(dto.getPriority());

        }

    }

}
