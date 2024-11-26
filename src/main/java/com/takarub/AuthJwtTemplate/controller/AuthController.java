package com.takarub.AuthJwtTemplate.controller;

import com.takarub.AuthJwtTemplate.dto.AuthenticationResponse;
import com.takarub.AuthJwtTemplate.dto.LoginRequest;
import com.takarub.AuthJwtTemplate.dto.RegisteredRequest;
import com.takarub.AuthJwtTemplate.service.AuthService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisteredRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid LoginRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.login(request));

    }
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        AuthenticationResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }


}
