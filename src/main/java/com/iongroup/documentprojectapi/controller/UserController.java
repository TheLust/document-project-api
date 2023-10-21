package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.PasswordChangeRequest;
import com.iongroup.documentprojectapi.dto.RegisterRequest;
import com.iongroup.documentprojectapi.dto.UserDto;
import com.iongroup.documentprojectapi.facade.UserFacade;
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
@RequestMapping("${api.url.base}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(
                userFacade.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestParam(value = "institution", required = false) Long institutionId,
                                        @RequestBody @Valid RegisterRequest registerRequest,
                                        BindingResult bindingResult) {
        return new ResponseEntity<>(
                userFacade.save(institutionId, registerRequest, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestParam("id") Long id,
                                          @RequestParam(value = "institution", required = false) Long institutionId,
                                          @RequestBody @Valid RegisterRequest registerRequest,
                                          BindingResult bindingResult) {
        return new ResponseEntity<>(
                userFacade.update(id, institutionId, registerRequest, bindingResult),
                HttpStatus.OK
        );
    }

    @PutMapping("/able")
    public ResponseEntity<UserDto> able(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                userFacade.able(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserDto> changePassword(@RequestParam("id") Long id,
                                                  @RequestBody @Valid PasswordChangeRequest passwordChangeRequest,
                                                  BindingResult bindingResult) {
        return new ResponseEntity<>(
                userFacade.changePassword(id, passwordChangeRequest, bindingResult),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<UserDto> delete(@RequestParam("id") Long id) {
        return new ResponseEntity<>(
                userFacade.delete(id),
                HttpStatus.OK
        );
    }
}
