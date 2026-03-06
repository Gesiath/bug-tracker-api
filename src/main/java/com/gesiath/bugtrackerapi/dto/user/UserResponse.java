package com.gesiath.bugtrackerapi.dto.user;

import com.gesiath.bugtrackerapi.enumerator.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private UUID id;
    private String name;
    private String username;
    private String email;
    private UserRole userRole;
    private LocalDateTime createdAt;

}
