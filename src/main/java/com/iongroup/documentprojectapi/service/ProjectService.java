package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.Project;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.ProjectRepository;
import com.iongroup.documentprojectapi.util.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Entity.PROJECT)
                );
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project oldProject, Project newProject) {
        BeanUtils.copyProperties(newProject, oldProject, "id", "user");
        return projectRepository.save(oldProject);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
