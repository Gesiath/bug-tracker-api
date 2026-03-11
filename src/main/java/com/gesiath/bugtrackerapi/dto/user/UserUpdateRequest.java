package com.gesiath.bugtrackerapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Email(message = "Email must be valid")
    @Size(max = 150)
    private String email;

    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

}
