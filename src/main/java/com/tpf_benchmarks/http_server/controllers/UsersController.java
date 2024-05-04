package com.tpf_benchmarks.http_server.controllers;

import com.tpf_benchmarks.http_server.dtos.AuthenticationRequest;
import com.tpf_benchmarks.http_server.dtos.AuthenticationResponse;
import com.tpf_benchmarks.http_server.dtos.RegisterRequest;
import com.tpf_benchmarks.http_server.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final AuthenticationService authService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

}
