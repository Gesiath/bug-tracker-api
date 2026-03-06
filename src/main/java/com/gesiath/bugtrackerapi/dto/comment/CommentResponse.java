package com.gesiath.bugtrackerapi.dto.comment;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID issueId;
    private String issueTitle;
    private String userName;

}
