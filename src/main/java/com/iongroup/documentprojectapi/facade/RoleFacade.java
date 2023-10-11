package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.RoleDto;
import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.mapper.RoleMapper;
import com.iongroup.documentprojectapi.service.RoleService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.RoleValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    private final RoleValidator roleValidator;

    public List<RoleDto> findAll() {
        return roleService.findAll()
                .stream()
                .map(roleMapper::toRoleDto)
                .toList();
    }

    public RoleDto save(@Valid RoleDto roleDto,
                        BindingResult bindingResult) {
        roleDto.setId(null);
        Role role = roleMapper.toRole(roleDto);

        roleValidator.validate(role, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return roleMapper.toRoleDto(
                roleService.save(role)
        );
    }

    public RoleDto update(Long id,
                          @Valid RoleDto roleDto,
                          BindingResult bindingResult) {
        Role oldRole = roleService.findById(id);
        Role newRole = roleMapper.toRole(roleDto);

        roleValidator.validate(id, newRole, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return roleMapper.toRoleDto(
                roleService.update(oldRole, newRole)
        );
    }

    public RoleDto delete(Long id) {
        Role role = roleService.findById(id);
        roleService.delete(id);

        return roleMapper.toRoleDto(role);
    }
}
