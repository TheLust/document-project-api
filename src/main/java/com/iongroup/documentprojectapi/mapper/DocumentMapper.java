package com.iongroup.documentprojectapi.mapper;

import com.iongroup.documentprojectapi.dto.DocumentDto;
import com.iongroup.documentprojectapi.entity.Document;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentMapper {

    private final ModelMapper mapper;

    public Document toDocument(DocumentDto documentDto) {
        return mapper.map(documentDto, Document.class);
    }

    public DocumentDto toDocumentDto(Document document) {
        return mapper.map(document, DocumentDto.class);
    }
}
