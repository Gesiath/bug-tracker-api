package com.gesiath.bugtrackerapi.service;

import com.gesiath.bugtrackerapi.dto.user.UserFilterRequest;
import com.gesiath.bugtrackerapi.dto.user.UserResponse;
import com.gesiath.bugtrackerapi.dto.user.UserUpdateRequest;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<UserResponse> getAll(
            UserFilterRequest filters,
            Pageable pageable);
    UserResponse getById(UUID id);
    UserResponse update(UUID id, UserUpdateRequest dto);
    UserResponse patchRole(UUID id, UserRole role);
    void delete(UUID id);
    Page<UserResponse> getDevelopers(Pageable pageable);
    UserResponse getCurrentUser();

}
