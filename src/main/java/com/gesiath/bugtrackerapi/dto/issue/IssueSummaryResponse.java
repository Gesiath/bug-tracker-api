package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.enumerator.Priority;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueSummaryResponse {

    private UUID id;
    private String title;
    private IssueStatus issueStatus;
    private Priority priority;

}
