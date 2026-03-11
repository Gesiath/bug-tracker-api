package com.gesiath.bugtrackerapi.dto.project;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ProjectFilterRequest {

    private UUID userId;

    private String search;

}
