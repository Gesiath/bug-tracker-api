package com.gesiath.bugtrackerapi.dto.user;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummaryResponse {

    private UUID id;
    private String name;
    private String username;

}
