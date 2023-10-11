package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.InstitutionDto;
import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.mapper.InstitutionMapper;
import com.iongroup.documentprojectapi.service.InstitutionService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.InstitutionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstitutionFacade {

    private final InstitutionService institutionService;

    private final InstitutionMapper institutionMapper;

    private final InstitutionValidator institutionValidator;

    public List<InstitutionDto> findAll() {
        return institutionService.findAll()
                .stream()
                .map(institutionMapper::toInstitutionDto)
                .toList();
    }

    public InstitutionDto save(@Valid InstitutionDto institutionDto,
                               BindingResult bindingResult) {
        institutionDto.setId(null);
        Institution institution = institutionMapper.toInstitution(institutionDto);

        institutionValidator.validate(institution, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return institutionMapper.toInstitutionDto(
                institutionService.save(institution)
        );
    }

    public InstitutionDto update(Long id,
                          @Valid InstitutionDto institutionDto,
                          BindingResult bindingResult) {
        Institution oldInstitution = institutionService.findById(id);
        Institution newInstitution = institutionMapper.toInstitution(institutionDto);

        institutionValidator.validate(id, newInstitution, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return institutionMapper.toInstitutionDto(
                institutionService.update(oldInstitution, newInstitution)
        );
    }

    public InstitutionDto delete(Long id) {
        Institution institution = institutionService.findById(id);
        institutionService.delete(id);

        return institutionMapper.toInstitutionDto(institution);
    }
}
