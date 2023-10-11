package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.InstitutionDto;
import com.iongroup.documentprojectapi.facade.InstitutionFacade;
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
@RequestMapping("${api.url.base}/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionFacade institutionFacade;

    @GetMapping
    public ResponseEntity<List<InstitutionDto>> findAll() {
        return new ResponseEntity<>(
                institutionFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<InstitutionDto> save(@RequestBody @Valid InstitutionDto institutionDto,
                                        BindingResult bindingResult) {
        return new ResponseEntity<>(
                institutionFacade.save(institutionDto, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<InstitutionDto> update(@RequestParam("id") Long id,
                                          @RequestBody @Valid InstitutionDto institutionDto,
                                          BindingResult bindingResult) {
        return new ResponseEntity<>(
                institutionFacade.update(id, institutionDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<InstitutionDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                institutionFacade.delete(id),
                HttpStatus.OK
        );
    }
}
