package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.DocumentTypeDto;
import com.iongroup.documentprojectapi.entity.DocumentType;
import com.iongroup.documentprojectapi.mapper.DocumentTypeMapper;
import com.iongroup.documentprojectapi.service.DocumentTypeService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.DocumentTypeValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentTypeFacade {

    private final DocumentTypeService documentTypeService;

    private final DocumentTypeMapper documentTypeMapper;

    private final DocumentTypeValidator documentTypeValidator;

    public List<DocumentTypeDto> findAll() {
        return documentTypeService.findAll()
                .stream()
                .map(documentTypeMapper::toDocumentTypeDto)
                .toList();
    }

    public DocumentTypeDto save(Long macroId,
                           @Valid DocumentTypeDto documentTypeDto,
                           BindingResult bindingResult) {
        documentTypeDto.setId(null);
        DocumentType documentType = documentTypeMapper.toDocumentType(documentTypeDto);

        if (macroId != null) {
            DocumentType macro = documentTypeService.findById(macroId);
            documentType.setMacro(macro);
        }

        documentTypeValidator.validate(documentType, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return documentTypeMapper.toDocumentTypeDto(
                documentTypeService.save(documentType)
        );
    }

    public DocumentTypeDto update(Long id,
                                  Long macroId,
                                  @Valid DocumentTypeDto documentTypeDto,
                                  BindingResult bindingResult) {
        DocumentType oldDocumentType = documentTypeService.findById(id);

        DocumentType newDocumentType = documentTypeMapper.toDocumentType(documentTypeDto);
        if (macroId != null) {
            newDocumentType.setMacro(documentTypeService.findById(macroId));
        } else {
            newDocumentType.setMacro(oldDocumentType.getMacro());
        }

        documentTypeValidator.validate(id, newDocumentType, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return documentTypeMapper.toDocumentTypeDto(
                documentTypeService.update(oldDocumentType, newDocumentType)
        );
    }

    public DocumentTypeDto delete(Long id) {
        DocumentType documentType = documentTypeService.findById(id);
        documentTypeService.delete(id);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }
}
