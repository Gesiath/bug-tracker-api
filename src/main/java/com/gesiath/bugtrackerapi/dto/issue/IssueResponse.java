package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.dto.user.UserResponse;
import com.gesiath.bugtrackerapi.dto.user.UserSummaryResponse;
import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.enumerator.Priority;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResponse {

    private UUID id;
    private String title;
    private String description;
    private IssueStatus issueStatus;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID projectId;
    private String projectName;
    private UserSummaryResponse reporter;
    private UserSummaryResponse assignee;

}
