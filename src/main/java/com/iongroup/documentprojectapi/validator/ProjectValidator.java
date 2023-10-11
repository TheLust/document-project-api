package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class ProjectValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;

        if (project.getStartDate().isAfter(project.getFinishDate())) {
            errors.rejectValue("startDate", "Start date must be before finish date");
        }
    }
}
