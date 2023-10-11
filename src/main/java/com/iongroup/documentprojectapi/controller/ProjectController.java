package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.ProjectDto;
import com.iongroup.documentprojectapi.facade.ProjectFacade;
import com.iongroup.documentprojectapi.security.AccountDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.url.base}/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll() {
        return new ResponseEntity<>(
                projectFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ProjectDto> save(@RequestParam("institution") Long institutionId,
                                           @AuthenticationPrincipal AccountDetails accountDetails,
                                           @RequestBody @Valid ProjectDto projectDto,
                                           BindingResult bindingResult) {
        return new ResponseEntity<>(
                projectFacade.save(institutionId, accountDetails.getUser(), projectDto, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ProjectDto> update(@RequestParam("id") Long id,
                                          @RequestParam(value = "institution", required = false) Long institutionId,
                                          @RequestBody @Valid ProjectDto projectDto,
                                          BindingResult bindingResult) {
        return new ResponseEntity<>(
                projectFacade.update(id, institutionId, projectDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<ProjectDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                projectFacade.delete(id),
                HttpStatus.OK
        );
    }
}
