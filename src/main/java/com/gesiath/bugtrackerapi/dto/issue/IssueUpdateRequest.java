package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.enumerator.Priority;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueUpdateRequest {

    private String title;
    private String description;
    private IssueStatus issueStatus;
    private Priority priority;
    private UUID assigneeId;

}
