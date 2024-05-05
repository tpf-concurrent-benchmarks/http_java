package com.tpf_benchmarks.http_server.services;

import com.tpf_benchmarks.http_server.dtos.CreatePollRequest;
import com.tpf_benchmarks.http_server.dtos.PollCreatedResponse;
import com.tpf_benchmarks.http_server.entities.Poll;
import com.tpf_benchmarks.http_server.entities.PollOption;
import com.tpf_benchmarks.http_server.entities.User;
import com.tpf_benchmarks.http_server.repositories.PollOptionRepository;
import com.tpf_benchmarks.http_server.repositories.PollRepository;
import com.tpf_benchmarks.http_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PollsService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public PollCreatedResponse createPoll(CreatePollRequest request, String authToken) {
        if (request.getOptions().length < 2) {
            throw new IllegalArgumentException("A poll must have at least 2 options");
        }
        if (request.getTitle().isEmpty()) {
            throw new IllegalArgumentException("A poll must have a title");
        }
        String userName = jwtService.extractUsername(authToken);
        User creator = userRepository.findByUsername(userName).orElseThrow();

        Poll poll = Poll.builder().pollTopic(request.getTitle()).user(creator).build();
        pollRepository.save(poll);
        String[] pollOptions = request.getOptions();
        for (int i = 0; i < pollOptions.length; i++) {
            var pollOptionId = PollOption.builder().poll(poll).optionNum(i).optionText(pollOptions[i]).build();
            pollOptionRepository.save(pollOptionId);
        }

        return new PollCreatedResponse(poll.getPollId());
    }
}
