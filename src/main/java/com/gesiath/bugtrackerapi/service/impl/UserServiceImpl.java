package com.gesiath.bugtrackerapi.service.impl;

import com.gesiath.bugtrackerapi.dto.user.UserResponse;
import com.gesiath.bugtrackerapi.dto.user.UserUpdateRequest;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.enumerator.UserRole;
import com.gesiath.bugtrackerapi.exception.CustomDataNotFoundException;
import com.gesiath.bugtrackerapi.mapper.UserMapper;
import com.gesiath.bugtrackerapi.repository.UserRepository;
import com.gesiath.bugtrackerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {

        return userRepository.findAll(pageable)
                .map(UserMapper::toResponse);

    }

    @Override
    public Page<UserResponse> getDevelopers(Pageable pageable) {

        return userRepository.findByRole(UserRole.DEVELOPER, pageable)
                .map(UserMapper::toResponse);

    }

    @Override
    public UserResponse getById(UUID id) {

        User user = findUser(id);

        return UserMapper.toResponse(user);

    }

    @Override
    public UserResponse getCurrentUser() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return UserMapper.toResponse(user);

    }

    @Override
    public UserResponse update(UUID id, UserUpdateRequest dto) {

        User user = findUser(id);

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

            user.setPassword(passwordEncoder.encode(dto.getPassword()));

        }

        userRepository.save(user);

        return UserMapper.toResponse(user);

    }

    @Override
    public UserResponse patchRole(UUID id, UserRole role) {

        User user = findUser(id);

        user.setRole(role);

        userRepository.save(user);

        return UserMapper.toResponse(user);

    }

    @Override
    public void delete(UUID id) {

        User user = findUser(id);

        userRepository.delete(user);

    }

    private User findUser(UUID id){

        return userRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("User not found"));

    }

}
