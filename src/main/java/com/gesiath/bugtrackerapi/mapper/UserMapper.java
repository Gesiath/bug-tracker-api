package com.gesiath.bugtrackerapi.mapper;

import com.gesiath.bugtrackerapi.dto.user.UserCreateRequest;
import com.gesiath.bugtrackerapi.dto.user.UserResponse;
import com.gesiath.bugtrackerapi.dto.user.UserSummaryResponse;
import com.gesiath.bugtrackerapi.dto.user.UserUpdateRequest;
import com.gesiath.bugtrackerapi.entity.User;

public class UserMapper {

    private UserMapper(){

    }

    public static UserResponse toResponse(User user){

        if (user == null) {

            return null;

        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .userRole(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();

    }

    public static UserSummaryResponse toSummary(User user){

        if (user == null){

            return null;

        }

        return UserSummaryResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();

    }

    public static User toEntity(UserCreateRequest dto){

        if (dto == null) {

            return null;

        }

        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

    }

    public static void  updateEntity(User user, UserUpdateRequest dto){

        if (dto.getName() != null){

            user.setName(dto.getName());

        }

        if (dto.getUsername() != null){

            user.setUsername(dto.getUsername());

        }

        if (dto.getEmail() != null){

            user.setEmail(dto.getEmail());

        }

        if (dto.getPassword() != null){

            user.setPassword(dto.getPassword());

        }

    }

}
