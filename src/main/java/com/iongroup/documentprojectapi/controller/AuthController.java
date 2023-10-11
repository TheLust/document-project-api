package com.iongroup.documentprojectapi.controller;

import com.iongroup.documentprojectapi.dto.LoginRequest;
import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.exception.AuthenticationException;
import com.iongroup.documentprojectapi.security.AccountDetails;
import com.iongroup.documentprojectapi.security.JwtUtils;
import com.iongroup.documentprojectapi.service.RoleService;
import com.iongroup.documentprojectapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("${api.url.base}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RoleService roleService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        try {
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
            return ResponseEntity.ok(jwtUtils.generateToken(accountDetails.getUser()));

        } catch (BadCredentialsException e) {
            throw new AuthenticationException(AuthenticationException.BAD_CREDENTIALS);
        }
    }

    @GetMapping("/init")
    public ResponseEntity<String> init() {
        User user = new User();
        user.setPassword("admin");
        user.setEmail("admin@admin.admin");
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("Amministratore").get());
        user.setRoles(roles);
        user.setIsEnabled(true);
        user.setName("Admin");
        user.setSurname("Admin");
        user.setPatronymic("Admin");
        user.setUsername("admin2");

        userService.save(user);

        return new ResponseEntity<>(
                "Init",
                HttpStatus.OK
        );
    }
}
