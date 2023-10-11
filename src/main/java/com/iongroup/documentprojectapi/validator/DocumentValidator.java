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
    }
}
