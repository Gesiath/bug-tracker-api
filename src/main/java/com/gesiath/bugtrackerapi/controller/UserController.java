package com.gesiath.bugtrackerapi.controller;


import com.gesiath.bugtrackerapi.dto.user.UserResponse;
import com.gesiath.bugtrackerapi.dto.user.UserUpdateRequest;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @PageableDefault(size = 10, sort = "name") Pageable pageable){

        return ResponseEntity.ok(userService.getAll(pageable));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/developers")
    public ResponseEntity<Page<UserResponse>> getDevelopers(
            @PageableDefault(size = 10, sort = "name") Pageable pageable){

        return ResponseEntity.ok(userService.getDevelopers(pageable));

    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(){

        return ResponseEntity.ok(userService.getCurrentUser());

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id){

        return ResponseEntity.ok(userService.getById(id));

    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id,
                                               @Valid @RequestBody UserUpdateRequest dto){

        return ResponseEntity.ok(userService.update(id, dto));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/role")
    public ResponseEntity<UserResponse> patchRole(@PathVariable UUID id,
                                                  @RequestParam UserRole role){

        return ResponseEntity.ok(userService.patchRole(id, role));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        userService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
