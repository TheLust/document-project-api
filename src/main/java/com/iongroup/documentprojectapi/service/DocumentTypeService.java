package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.DocumentType;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.DocumentTypeRepository;
import com.iongroup.documentprojectapi.util.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentType findById(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Entity.DOCUMENT_TYPE)
                );
    }

    public Optional<DocumentType> findByCode(String code) {
        return documentTypeRepository.findByCode(code);
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
