package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.ProjectDto;
import com.iongroup.documentprojectapi.entity.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ModelMapper mapper;

    public Project toProject(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    public ProjectDto toProjectDto(Project project) {
        return mapper.map(project, ProjectDto.class);
    }
}
