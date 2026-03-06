package com.gesiath.bugtrackerapi.dto.issue;

import com.gesiath.bugtrackerapi.enumerator.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "ID of the project is required")
    private UUID projectId;

    @NotNull(message = "The ID of the user to whom the issue will be assigned is required")
    private UUID assigneeId;

}
