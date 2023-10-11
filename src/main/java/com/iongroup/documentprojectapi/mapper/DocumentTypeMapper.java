package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.DocumentTypeDto;
import com.iongroup.documentprojectapi.entity.DocumentType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentTypeMapper {

    private final ModelMapper mapper;

    public DocumentType toDocumentType(DocumentTypeDto documentTypeDto) {
        return mapper.map(documentTypeDto, DocumentType.class);
    }

    public DocumentTypeDto toDocumentTypeDto(DocumentType documentType) {
        return mapper.map(documentType, DocumentTypeDto.class);
    }
}
