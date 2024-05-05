package com.tpf_benchmarks.http_server.controllers;

import com.tpf_benchmarks.http_server.dtos.CreatePollRequest;
import com.tpf_benchmarks.http_server.dtos.PollCreatedResponse;
import com.tpf_benchmarks.http_server.services.PollsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
@Tag(name = "Polls")
public class PollsController {
    private final PollsService pollsService;

    @PostMapping
    public ResponseEntity<PollCreatedResponse> register(
            @RequestBody CreatePollRequest request, @RequestHeader("Authorization") String authToken
    ) {
        String token = authToken.substring(7);
        return ResponseEntity.ok(pollsService.createPoll(request, token));
    }
}
