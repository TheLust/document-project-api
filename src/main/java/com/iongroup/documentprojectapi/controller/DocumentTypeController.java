package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.DocumentTypeDto;
import com.iongroup.documentprojectapi.facade.DocumentTypeFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.url.base}/types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeFacade documentTypeFacade;

    @GetMapping
    public ResponseEntity<List<DocumentTypeDto>> findAll() {
        return new ResponseEntity<>(
                documentTypeFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DocumentTypeDto> save(@RequestParam(value = "macro", required = false) Long macroId,
                                                @RequestBody @Valid DocumentTypeDto documentTypeDto,
                                                BindingResult bindingResult) {
        return new ResponseEntity<>(
                documentTypeFacade.save(macroId, documentTypeDto, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<DocumentTypeDto> update(@RequestParam("id") Long id,
                                                  @RequestParam(value = "macro", required = false) Long macroId,
                                                  @RequestBody @Valid DocumentTypeDto documentTypeDto,
                                                  BindingResult bindingResult) {
        return new ResponseEntity<>(
                documentTypeFacade.update(id, macroId, documentTypeDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<DocumentTypeDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                documentTypeFacade.delete(id),
                HttpStatus.OK
        );
    }
}
