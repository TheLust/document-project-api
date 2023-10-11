package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.annotation.Alphanumeric;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentTypeDto {

    private Long id;

    private DocumentTypeDto macro;

    @NotBlank(message = Field.CODE + ConstraintViolationMessage.NOT_BLANK)
    @Size(
            min = 1,
            max = 5,
            message = Field.CODE + ConstraintViolationMessage.SIZE
    )
    @Alphanumeric(message = Field.CODE + ConstraintViolationMessage.ALPHANUMERIC)
    private String code;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;

    private String description;

    @NotNull(message = Field.DATE_GROUPED + ConstraintViolationMessage.NOT_NULL)
    private Boolean isDateGrouped;
}
