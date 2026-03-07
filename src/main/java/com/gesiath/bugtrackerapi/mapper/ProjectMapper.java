package com.gesiath.bugtrackerapi.mapper;

import com.gesiath.bugtrackerapi.dto.project.ProjectCreateRequest;
import com.gesiath.bugtrackerapi.dto.project.ProjectResponse;
import com.gesiath.bugtrackerapi.dto.project.ProjectUpdateRequest;
import com.gesiath.bugtrackerapi.entity.Project;

public class ProjectMapper {

    private ProjectMapper(){

    }

    public static ProjectResponse toResponse(Project project){

        if (project == null) {

            return null;

        }

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .createdByUserName(
                        project.getCreatedByUser() != null
                                ? project.getCreatedByUser().getName()
                                : null
                )
                .build();

    }

    public static Project toEntity(ProjectCreateRequest dto){

        if (dto == null) {

            return null;

        }

        return Project.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

    }

    public static void updateEntity(Project project, ProjectUpdateRequest dto){

        if (dto.getName() != null){

            project.setName(dto.getName());

        }

        if (dto.getDescription() != null){

            project.setDescription(dto.getDescription());

        }

    }

}
