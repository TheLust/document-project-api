package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.RegisterRequest;
import com.iongroup.documentprojectapi.dto.UserDto;
import com.iongroup.documentprojectapi.facade.UserFacade;
import com.iongroup.documentprojectapi.security.AccountDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.url.base}/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final UserFacade userFacade;

    @GetMapping
    public ResponseEntity<UserDto> find(@AuthenticationPrincipal AccountDetails accountDetails) {
        return new ResponseEntity<>(
                userFacade.personalFind(accountDetails.getUser()),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<UserDto> updateMyAccount(@AuthenticationPrincipal AccountDetails accountDetails,
                                                   @RequestBody @Valid RegisterRequest registerRequest,
                                                   BindingResult bindingResult) {
        return new ResponseEntity<>(
                userFacade.update(accountDetails.getUser().getId(), null, registerRequest, bindingResult),
                HttpStatus.OK
        );
    }
}
