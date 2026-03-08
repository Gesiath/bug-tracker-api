package com.gesiath.bugtrackerapi.dto.project;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStatsResponse {

    private long totalIssues;
    private long openIssues;
    private long inProgressIssues;
    private long resolvedIssues;
    private long closedIssues;

}
