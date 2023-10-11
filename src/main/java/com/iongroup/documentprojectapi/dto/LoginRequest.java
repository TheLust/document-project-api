package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = Field.USERNAME + ConstraintViolationMessage.NOT_BLANK)
    private String username;

    @NotBlank(message = Field.PASSWORD + ConstraintViolationMessage.NOT_BLANK)
    private String password;
}
