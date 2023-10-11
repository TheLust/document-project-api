package com.iongroup.documentprojectapi.dto;

import com.iongroup.documentprojectapi.annotation.Alphanumeric;
import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.util.ConstraintViolationMessage;
import com.iongroup.documentprojectapi.util.Field;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterRequest {

    private Long id;

    private InstitutionDto institution;

    private Set<Long> roleIds;

    @NotBlank(message = Field.USERNAME + ConstraintViolationMessage.NOT_BLANK)
    @Size(
            min = 1,
            max = 32,
            message = Field.USERNAME + ConstraintViolationMessage.SIZE
    )
    @Alphanumeric(message = Field.USERNAME + ConstraintViolationMessage.ALPHANUMERIC)
    private String username;

    @NotBlank(message = Field.PASSWORD + ConstraintViolationMessage.NOT_BLANK)
    @Alphanumeric(message = Field.PASSWORD + ConstraintViolationMessage.ALPHANUMERIC)
    private String password;

    @NotBlank(message = Field.EMAIL + ConstraintViolationMessage.NOT_BLANK)
    @Email(message = Field.EMAIL + ConstraintViolationMessage.INVALID)
    private String email;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;

    @NotBlank(message = Field.SURNAME + ConstraintViolationMessage.NOT_BLANK)
    private String surname;

    private String patronymic;

    @NotNull(message = Field.ENABLED + ConstraintViolationMessage.NOT_NULL)
    private Boolean isEnabled;
}
