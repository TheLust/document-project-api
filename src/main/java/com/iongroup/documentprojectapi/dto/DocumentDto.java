package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.annotation.Alphanumeric;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DocumentDto {

    private Long id;

    private InstitutionDto institution;

    private UserDto user;

    private DocumentTypeDto type;

    private ProjectDto project;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;

    private String path;

    private LocalDate uploadDate;

    @NotBlank(message = Field.ADDITIONAL_INFORMATION + ConstraintViolationMessage.NOT_BLANK)
    @Alphanumeric(message = Field.ADDITIONAL_INFORMATION + ConstraintViolationMessage.ALPHANUMERIC)
    private String additionalInformation;

    @NotNull(message = Field.GROUPING_DATE + ConstraintViolationMessage.NOT_NULL)
    private LocalDate groupingDate;
}
