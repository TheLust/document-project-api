package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.annotation.Alphanumeric;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionDto {

    private Long id;

    @NotBlank(message = Field.CODE + ConstraintViolationMessage.NOT_BLANK)
    @Size(
            min = 1,
            max = 5,
            message = Field.CODE + ConstraintViolationMessage.SIZE

    )
    @Alphanumeric(message = Field.CODE + ConstraintViolationMessage.ALPHANUMERIC)
    private String code;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    @Alphanumeric(message = Field.NAME + ConstraintViolationMessage.ALPHANUMERIC)
    private String name;

    private String additionalInformation;
}
