package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.DocumentDto;
import com.iongroup.documentprojectapi.facade.DocumentFacade;
import com.iongroup.documentprojectapi.security.AccountDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.url.base}/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentFacade documentFacade;

    @GetMapping
    public ResponseEntity<List<DocumentDto>> findAll() {
        return new ResponseEntity<>(
                documentFacade.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/my-institution")
    public ResponseEntity<List<DocumentDto>> findAllForBankOperator(@AuthenticationPrincipal AccountDetails accountDetails) {
        return  new ResponseEntity<>(
                documentFacade.getAllForInstitution(accountDetails.getUser()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@RequestParam("id") Long id,
                                             @AuthenticationPrincipal AccountDetails accountDetails) {
        return new ResponseEntity<>(
                documentFacade.download(id, accountDetails.getUser()),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DocumentDto> save(@RequestParam(value = "institution") Long institutionId,
                                            @RequestParam(value = "project", required = false) Long projectId,
                                            @RequestParam(value = "type") Long typeId,
                                            @RequestPart(value = "file") MultipartFile multipartFile,
                                            @AuthenticationPrincipal AccountDetails accountDetails,
                                            @RequestPart(value = "document") @Valid DocumentDto documentDto,
                                            BindingResult bindingResult) {
        return new ResponseEntity<>(
                documentFacade.save(institutionId, projectId, typeId, accountDetails.getUser(), multipartFile, documentDto, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<DocumentDto> update(@RequestParam(value = "id") Long id,
                                              @RequestBody @Valid DocumentDto documentDto,
                                              BindingResult bindingResult) {
        return new ResponseEntity<>(
                documentFacade.update(id, documentDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<DocumentDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                documentFacade.delete(id),
                HttpStatus.OK
        );
    }
}
