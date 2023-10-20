package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.annotation.Alphanumeric;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {

    @NotBlank(message = Field.PASSWORD + ConstraintViolationMessage.NOT_BLANK)
    @Alphanumeric(message = Field.PASSWORD + ConstraintViolationMessage.ALPHANUMERIC)
    private String password;
}
