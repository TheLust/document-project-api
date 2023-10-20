package com.iongroup.documentprojectapi.validator;

import com.iongroup.documentprojectapi.entity.Document;
import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.service.DocumentService;
import com.iongroup.documentprojectapi.util.Entity;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class DocumentValidator implements Validator {

    private final DocumentService documentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Document.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Document document = (Document) target;

        if (documentService.findByPath(document.getPath()).isPresent()) {
            errors.rejectValue("path", AlreadyExistsException.getMessage(Entity.DOCUMENT, Field.PATH));
        }

        if (document.getType().getMacro() == null && !document.getType().getMicros().isEmpty()) {
            errors.rejectValue("type", "Cannot set macro document type");
        }

        if (document.getType().getMacro() != null) {
            if (!document.getType().getMacro().getName().equals("Progettazione") && document.getProject() != null) {
                errors.rejectValue("project", "Cannot set project for this document type");
            }
        }

        if (document.getProject() != null && document.getInstitution() != null) {
            if (!document.getProject().getInstitution().getId().equals(document.getInstitution().getId())) {
                errors.rejectValue("project", "Project must be from the same institution");
            }
        }
    }
}
