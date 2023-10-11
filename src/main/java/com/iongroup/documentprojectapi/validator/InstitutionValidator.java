package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.service.InstitutionService;
import com.iongroup.documentprojectapi.util.Entity;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class InstitutionValidator implements Validator {

    private final InstitutionService institutionService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Institution.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Institution institution = (Institution) target;

        if (institutionService.findByCode(institution.getCode()).isPresent()) {
            errors.rejectValue("code", AlreadyExistsException.getMessage(Entity.INSTITUTION, Field.CODE));
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Institution institution = (Institution) target;
        Optional<Institution> foundInstitution = institutionService.findByCode(institution.getCode());

        if (foundInstitution.isPresent() && !foundInstitution.get().getId().equals(id)) {
            errors.rejectValue("code", AlreadyExistsException.getMessage(Entity.INSTITUTION, Field.CODE));
        }
    }
}
