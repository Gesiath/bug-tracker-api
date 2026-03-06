package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueUpdateStatusRequest {

    @NotNull(message = "Status is required")
    private IssueStatus issueStatus;

}
