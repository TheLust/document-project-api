package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

    private Long id;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;
}
