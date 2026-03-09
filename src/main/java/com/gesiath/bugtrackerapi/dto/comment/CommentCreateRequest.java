package com.gesiath.bugtrackerapi.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateRequest {

    @NotBlank(message = "Content is required")
    @Size(max = 500)
    private String content;

}
