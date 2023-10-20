package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.PasswordChangeRequest;
import com.iongroup.documentprojectapi.dto.RegisterRequest;
import com.iongroup.documentprojectapi.dto.UserDto;
import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.mapper.UserMapper;
import com.iongroup.documentprojectapi.service.InstitutionService;
import com.iongroup.documentprojectapi.service.RoleService;
import com.iongroup.documentprojectapi.service.UserService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final InstitutionService institutionService;

    private final RoleService roleService;

    private final UserMapper userMapper;

    private final UserValidator userValidator;

    public List<UserDto> findAll() {
        return userService.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto save(Long institutionId,
                        @Valid RegisterRequest registerRequest,
                        BindingResult bindingResult) {
        Institution institution = institutionService.findById(institutionId);

        User user = userMapper.toUser(registerRequest);
        user.setInstitution(institution);
        user.setRoles(
                registerRequest.getRoles()
                        .stream()
                        .map(roleDto -> roleService.findById(roleDto.getId()))
                        .collect(Collectors.toSet())
        );

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return userMapper.toUserDto(
                userService.save(user)
        );
    }

    public UserDto update(Long id,
                          Long institutionId,
                          @Valid RegisterRequest registerRequest,
                          BindingResult bindingResult) {
        User oldUser = userService.findById(id);

        User newUser = userMapper.toUser(registerRequest);
        if (institutionId != null) {
            newUser.setInstitution(institutionService.findById(institutionId));
        } else {
            newUser.setInstitution(oldUser.getInstitution());
        }
        newUser.setRoles(
                registerRequest.getRoles()
                        .stream()
                        .map(roleDto -> roleService.findById(roleDto.getId()))
                        .collect(Collectors.toSet())
        );

        userValidator.validate(id, newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return userMapper.toUserDto(
                userService.update(oldUser, newUser)
        );
    }

    public UserDto able(Long id) {
        User oldUser = userService.findById(id);

        oldUser.setIsEnabled(!oldUser.getIsEnabled());

        return userMapper.toUserDto(
                userService.update(oldUser, oldUser)
        );
    }

    public UserDto changePassword(Long id,
                                  PasswordChangeRequest passwordChangeRequest,
                                  BindingResult bindingResult) {
        User oldUser = userService.findById(id);

        oldUser.setPassword(passwordChangeRequest.getPassword());

        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return userMapper.toUserDto(
                userService.update(oldUser, oldUser)
        );
    }

    public UserDto delete(Long id) {
        User user = userService.findById(id);
        userService.delete(id);

        return userMapper.toUserDto(user);
    }
}
