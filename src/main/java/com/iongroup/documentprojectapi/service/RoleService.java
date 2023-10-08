package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.RoleRepository;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Field.ROLE)
                );
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role oldRole, Role newRole) {
        BeanUtils.copyProperties(newRole, oldRole, "id");
        return roleRepository.save(oldRole);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
