package com.gesiath.bugtrackerapi.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUpdateRequest {

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 500)
    private String description;

}
