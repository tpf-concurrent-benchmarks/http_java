package com.tpf_benchmarks.http_server.controllers;

import com.tpf_benchmarks.http_server.dtos.LoginResponse;
import com.tpf_benchmarks.http_server.dtos.CreateUserRequest;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UsersController {

    private final AuthenticationService authService;

    @PostMapping
    @ApiResponse(description = "Register a new user", responseCode = "200")
    @ApiResponse(description = "User already exists", responseCode = "404")
    public ResponseEntity<LoginResponse> register(
            @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

}
