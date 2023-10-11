package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.Document;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.DocumentRepository;
import com.iongroup.documentprojectapi.util.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Document findById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Entity.DOCUMENT)
                );
    }

    public Optional<Document> findByPath(String path) {
        return documentRepository.findByPath(path);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public Document update(Document oldDocument, Document newDocument) {
        BeanUtils.copyProperties(newDocument, oldDocument,
                "id", "institution", "project", "user", "type", "path", "uploadDate");
        return documentRepository.save(oldDocument);
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}
