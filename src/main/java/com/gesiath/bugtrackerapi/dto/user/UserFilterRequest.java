package com.gesiath.bugtrackerapi.dto.user;

import com.gesiath.bugtrackerapi.enumerator.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserFilterRequest {

    private UserRole userRole;

}
