package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.ProjectDto;
import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.entity.Project;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.mapper.ProjectMapper;
import com.iongroup.documentprojectapi.service.InstitutionService;
import com.iongroup.documentprojectapi.service.ProjectService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.ProjectValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;

    private final InstitutionService institutionService;

    private final ProjectMapper projectMapper;

    private final ProjectValidator projectValidator;

    public List<ProjectDto> findAll() {
        return projectService.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .toList();
    }

    public ProjectDto save(Long institutionId,
                           User user,
                           @Valid ProjectDto projectDto,
                           BindingResult bindingResult) {
        projectDto.setId(null);
        Institution institution = institutionService.findById(institutionId);

        Project project = projectMapper.toProject(projectDto);
        project.setInstitution(institution);
        project.setUser(user);

        projectValidator.validate(project, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return projectMapper.toProjectDto(
                projectService.save(project)
        );
    }

    public ProjectDto update(Long id,
                             Long institutionId,
                             @Valid ProjectDto projectDto,
                             BindingResult bindingResult) {
        Project oldProject = projectService.findById(id);

        Project newProject = projectMapper.toProject(projectDto);
        if (institutionId != null) {
            newProject.setInstitution(institutionService.findById(institutionId));
        } else {
            newProject.setInstitution(oldProject.getInstitution());
        }

        projectValidator.validate(newProject, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return projectMapper.toProjectDto(
                projectService.update(oldProject, newProject)
        );
    }

    public ProjectDto delete(Long id) {
        Project project = projectService.findById(id);
        projectService.delete(id);

        return projectMapper.toProjectDto(project);
    }
}
