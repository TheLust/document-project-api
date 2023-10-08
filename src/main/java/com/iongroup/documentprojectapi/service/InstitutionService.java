package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.InstitutionRepository;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public Institution findById(Long id) {
        return institutionRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Field.INSTITUTION)
                );
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }

    public Institution update(Institution oldInstitution, Institution newInstitution) {
        BeanUtils.copyProperties(newInstitution, oldInstitution, "id");
        return institutionRepository.save(newInstitution);
    }

    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }
}
