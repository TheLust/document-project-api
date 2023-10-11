package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.RoleDto;
import com.iongroup.documentprojectapi.entity.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper mapper;

    public Role toRole(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }

    public RoleDto toRoleDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }
}
