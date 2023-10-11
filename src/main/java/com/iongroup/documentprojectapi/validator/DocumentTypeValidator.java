package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.DocumentType;
import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.service.DocumentTypeService;
import com.iongroup.documentprojectapi.util.Entity;
import com.iongroup.documentprojectapi.util.ExceptionMessage;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class DocumentTypeValidator implements Validator {

    private final DocumentTypeService documentTypeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DocumentType.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DocumentType documentType =  (DocumentType) target;

        if (documentTypeService.findByCode(documentType.getCode()).isPresent()) {
            errors.rejectValue("code", AlreadyExistsException.getMessage(Entity.DOCUMENT_TYPE, Field.CODE));
        }

        if (documentType.getMacro() != null) {
            if (documentType.getMacro().getMacro() != null) {
                errors.rejectValue("macro", ExceptionMessage.INCORRECT_MICRO_REFERENCE);
            }
        }
    }

    public void validate(Long id, Object target, Errors errors) {
        DocumentType documentType =  (DocumentType) target;
        Optional<DocumentType> foundDocumentType = documentTypeService.findByCode(documentType.getCode());

        if (foundDocumentType.isPresent() && !foundDocumentType.get().getId().equals(id)) {
            errors.rejectValue("code", AlreadyExistsException.getMessage(Entity.DOCUMENT_TYPE, Field.CODE));
        }

        if (documentType.getMacro() != null) {
            if (documentType.getMacro().getMacro() != null) {
                errors.rejectValue("macro", ExceptionMessage.INCORRECT_MICRO_REFERENCE);
            }

            if (documentType.getMacro().getId().equals(id)) {
                errors.rejectValue("macro", ExceptionMessage.SELF_REFERENCE);
            }
        }

        if (!documentType.getMicros().isEmpty() && documentType.getMacro() != null) {
            errors.rejectValue("macro", ExceptionMessage.INCORRECT_MACRO_REFERENCE);
        }
    }
}
