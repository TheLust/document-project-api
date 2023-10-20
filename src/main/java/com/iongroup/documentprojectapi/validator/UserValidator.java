package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.service.UserService;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
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
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", AlreadyExistsException.getMessage(Entity.USER, Field.USERNAME));
        }

        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", Entity.USER + " should have at least 1 " + Entity.ROLE.toLowerCase());
        }

        if (user.getRoles().stream().map(Role::getName).toList().contains("Operatore Bancare") && user.getInstitution() == null) {
            errors.rejectValue("institution", Entity.INSTITUTION + ConstraintViolationMessage.NOT_NULL);
        }

        if (!user.getRoles().stream().map(Role::getName).toList().contains("Operatore Bancare") && user.getInstitution() != null) {
            errors.rejectValue("institution", Entity.INSTITUTION + " must be null");
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        User user = (User) target;
        Optional<User> foundUser = userService.findByUsername(user.getUsername());

        if (foundUser.isPresent() && !foundUser.get().getId().equals(id)) {
            errors.rejectValue("username", AlreadyExistsException.getMessage(Entity.USER, Field.USERNAME));
        }

        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", Entity.USER + " should have at least 1 " + Entity.ROLE.toLowerCase());
        }
    }
}
