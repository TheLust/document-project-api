package com.iongroup.documentprojectapi.facade;

import com.iongroup.documentprojectapi.dto.DocumentDto;
import com.iongroup.documentprojectapi.entity.Document;
import com.iongroup.documentprojectapi.entity.DocumentType;
import com.iongroup.documentprojectapi.entity.Institution;
import com.iongroup.documentprojectapi.entity.Project;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.io.FileManager;
import com.iongroup.documentprojectapi.mapper.DocumentMapper;
import com.iongroup.documentprojectapi.service.DocumentService;
import com.iongroup.documentprojectapi.service.DocumentTypeService;
import com.iongroup.documentprojectapi.service.InstitutionService;
import com.iongroup.documentprojectapi.service.ProjectService;
import com.iongroup.documentprojectapi.util.ErrorUtils;
import com.iongroup.documentprojectapi.validator.DocumentValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentFacade {

    private final DocumentService documentService;

    private final InstitutionService institutionService;

    private final ProjectService projectService;

    private final DocumentTypeService documentTypeService;

    private final DocumentValidator documentValidator;

    public final DocumentMapper documentMapper;

    public List<DocumentDto> findAll() {
        return documentService.findAll()
                .stream()
                .map(documentMapper::toDocumentDto)
                .toList();
    }

    public List<DocumentDto> getAllForInstitution(User user) {
        return documentService.findAllByInstitution(user.getInstitution())
                .stream()
                .map(documentMapper::toDocumentDto)
                .toList();
    }

    public Resource download(Long id,
                             User user) {
        Document document = documentService.findById(id);

        if (!user.getInstitution().getId().equals(document.getInstitution().getId())) {
            throw new RuntimeException("You dont have access to this institution files");
        }

        return FileManager.getFile(document);
    }

    public DocumentDto save(Long institutionId,
                            Long projectId,
                            Long typeId,
                            User user,
                            MultipartFile multipartFile,
                            @Valid DocumentDto documentDto,
                            BindingResult bindingResult) {
        documentDto.setId(null);
        Institution institution = institutionService.findById(institutionId);
        Project project = projectId != null ? projectService.findById(projectId) : null;
        DocumentType documentType = documentTypeService.findById(typeId);

        Document document = documentMapper.toDocument(documentDto);
        document.setInstitution(institution);
        document.setProject(project);
        document.setType(documentType);
        document.setUser(user);
        document.setUploadDate(LocalDate.now());
        document.setPath(FileManager.saveFile(document, multipartFile));

        documentValidator.validate(document, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return documentMapper.toDocumentDto(
                documentService.save(document)
        );
    }

    public DocumentDto update(Long id,
                              @Valid DocumentDto documentDto,
                              BindingResult bindingResult) {
        Document oldDocument = documentService.findById(id);
        Document newDocument = documentMapper.toDocument(documentDto);

        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrors(bindingResult);
        }

        return documentMapper.toDocumentDto(
                documentService.update(oldDocument, newDocument)
        );
    }

    public DocumentDto delete(Long id) {
        Document document = documentService.findById(id);
        documentService.delete(id);

        return documentMapper.toDocumentDto(document);
    }
}
