package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.DocumentType;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.DocumentTypeRepository;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentType findById(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Field.DOCUMENT_TYPE)
                );
    }

    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public DocumentType update(DocumentType oldDocumentType, DocumentType newDocumentType) {
        BeanUtils.copyProperties(newDocumentType, oldDocumentType, "id");
        return documentTypeRepository.save(oldDocumentType);
    }

    public void delete(Long id) {
        documentTypeRepository.deleteById(id);
    }
}
