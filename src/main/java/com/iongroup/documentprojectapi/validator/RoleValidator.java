package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.service.RoleService;
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
public class RoleValidator implements Validator {

    private final RoleService roleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role) target;

        if (roleService.findByName(role.getName()).isPresent()) {
            errors.rejectValue("name", AlreadyExistsException.getMessage(Entity.ROLE, Field.NAME));
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        Role role = (Role) target;
        Optional<Role> foundRole = roleService.findByName(role.getName());

        if (foundRole.isPresent() && !foundRole.get().getId().equals(id)) {
            errors.rejectValue("name", AlreadyExistsException.getMessage(Entity.ROLE, Field.NAME));
        }
    }
}
