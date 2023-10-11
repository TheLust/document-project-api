package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.InstitutionDto;
import com.iongroup.documentprojectapi.entity.Institution;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionMapper {

    private final ModelMapper mapper;

    public Institution toInstitution(InstitutionDto institutionDto) {
        return mapper.map(institutionDto, Institution.class);
    }

    public InstitutionDto toInstitutionDto(Institution institution) {
        return mapper.map(institution, InstitutionDto.class);
    }
}
