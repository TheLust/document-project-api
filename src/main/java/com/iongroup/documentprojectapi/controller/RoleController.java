package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.RoleDto;
import com.iongroup.documentprojectapi.facade.RoleFacade;
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
@RequestMapping("${api.url.base}/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleFacade roleFacade;

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll() {
        return new ResponseEntity<>(
                roleFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<RoleDto> save(@RequestBody @Valid RoleDto roleDto,
                                        BindingResult bindingResult) {
        return new ResponseEntity<>(
                roleFacade.save(roleDto, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<RoleDto> update(@RequestParam("id") Long id,
                                          @RequestBody @Valid RoleDto roleDto,
                                          BindingResult bindingResult) {
        return new ResponseEntity<>(
                roleFacade.update(id, roleDto, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<RoleDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                roleFacade.delete(id),
                HttpStatus.OK
        );
    }
}
