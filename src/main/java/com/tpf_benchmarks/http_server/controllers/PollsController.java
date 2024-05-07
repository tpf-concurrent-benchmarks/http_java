package com.tpf_benchmarks.http_server.controllers;

import com.tpf_benchmarks.http_server.dtos.CreatePollRequest;
import com.tpf_benchmarks.http_server.dtos.GetAllPollsResponse;
import com.tpf_benchmarks.http_server.dtos.PollCreatedResponse;
import com.tpf_benchmarks.http_server.dtos.PollsResponse;
import com.tpf_benchmarks.http_server.services.JwtService;
import com.tpf_benchmarks.http_server.services.PollsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final JwtService jwtService;

    @PostMapping
    @ApiResponse(description = "Creates a new poll", responseCode = "200")
    public ResponseEntity<PollCreatedResponse> register(
            @RequestBody CreatePollRequest request, @RequestHeader("Authorization") String authToken
    ) {
        String userName = extractUsername(authToken);
        return ResponseEntity.ok(pollsService.createPoll(request, userName));
    }

    @PostMapping("/{poll_id}/vote")
    @ApiResponse(description = "Votes an option in a poll", responseCode = "200")
    public ResponseEntity<Void> vote(
            @PathVariable("poll_id") int pollId, @RequestParam("option") int optionId, @RequestHeader("Authorization") String authToken
    ) {
        String userName = extractUsername(authToken);
        pollsService.votePollOption(pollId, optionId, userName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{poll_id}")
    public PollsResponse getPoll(@PathVariable("poll_id") int pollId) {
        return pollsService.getPoll(pollId);
    }

    @GetMapping
    public GetAllPollsResponse getPolls() {
        return pollsService.getPolls();
    }

    @DeleteMapping("/{poll_id}")
    public ResponseEntity<Void> deletePoll(@PathVariable("poll_id") int pollId, @RequestHeader("Authorization") String authToken) {
        String userName = extractUsername(authToken);
        pollsService.deletePoll(pollId, userName);
        return ResponseEntity.ok().build();
    }

    private String extractUsername(String authToken) {
        String token = authToken.substring(7);
        return jwtService.extractUsername(token);
    }

}
