package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.embeddable.DocumentTypeHierarchyPrimaryKey;
import com.iongroup.documentprojectapi.entity.DocumentTypeHierarchy;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.DocumentTypeHierarchyRepository;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeHierarchyService {

    private final DocumentTypeHierarchyRepository documentTypeHierarchyRepository;

    public DocumentTypeHierarchy findById(DocumentTypeHierarchyPrimaryKey id) {
        return documentTypeHierarchyRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Field.DOCUMENT_TYPE_HIERARCHY)
                );
    }

    public List<DocumentTypeHierarchy> findAll() {
        return documentTypeHierarchyRepository.findAll();
    }

    public DocumentTypeHierarchy save(DocumentTypeHierarchy documentTypeHierarchy) {
        return documentTypeHierarchyRepository.save(documentTypeHierarchy);
    }

    public DocumentTypeHierarchy update(DocumentTypeHierarchy oldDocumentTypeHierarchy,
                                        DocumentTypeHierarchy newDocumentTypeHierarchy) {
        BeanUtils.copyProperties(newDocumentTypeHierarchy, oldDocumentTypeHierarchy, "id");
        return documentTypeHierarchyRepository.save(oldDocumentTypeHierarchy);
    }

    public void delete(DocumentTypeHierarchyPrimaryKey id) {
        documentTypeHierarchyRepository.deleteById(id);
    }
}
