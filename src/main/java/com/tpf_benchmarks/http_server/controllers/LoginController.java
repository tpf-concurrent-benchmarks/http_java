package com.tpf_benchmarks.http_server.controllers;

import com.tpf_benchmarks.http_server.dtos.LoginRequest;
import com.tpf_benchmarks.http_server.dtos.LoginResponse;
import com.tpf_benchmarks.http_server.services.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@Tag(name = "Login")
public class LoginController {

    private final AuthenticationService authService;

    @PostMapping
    @ApiResponse(description = "Login a user", responseCode = "200")
    @ApiResponse(description = "Invalid credentials", responseCode = "401")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
