package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.enumerator.Priority;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class IssueFilterRequest {

    private IssueStatus issueStatus;

    private Priority priority;

    private UUID assigneeId;

    private UUID projectId;

    private String search;

}
