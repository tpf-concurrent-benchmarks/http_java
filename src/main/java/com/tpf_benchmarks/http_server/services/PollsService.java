package com.tpf_benchmarks.http_server.services;

import com.tpf_benchmarks.http_server.dtos.*;
import com.tpf_benchmarks.http_server.entities.Poll;
import com.tpf_benchmarks.http_server.entities.PollOption;
import com.tpf_benchmarks.http_server.entities.User;
import com.tpf_benchmarks.http_server.entities.Vote;
import com.tpf_benchmarks.http_server.exceptions.PollNotFoundException;
import com.tpf_benchmarks.http_server.exceptions.PollOptionNotFoundException;
import com.tpf_benchmarks.http_server.exceptions.UserIsNotCreatorException;
import com.tpf_benchmarks.http_server.exceptions.UserNotFoundException;
import com.tpf_benchmarks.http_server.repositories.PollOptionRepository;
import com.tpf_benchmarks.http_server.repositories.PollRepository;
import com.tpf_benchmarks.http_server.repositories.UserRepository;
import com.tpf_benchmarks.http_server.repositories.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PollsService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public PollCreatedResponse createPoll(CreatePollRequest request, String userName) {
        User creator = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found", userName)));

        Poll poll = Poll.builder().pollTopic(request.getTitle()).user(creator).build();
        pollRepository.save(poll);
        String[] pollOptions = request.getOptions();
        for (int i = 0; i < pollOptions.length; i++) {
            var pollOption = PollOption.builder().poll(poll).optionNum(i + 1).optionText(pollOptions[i]).build();
            pollOptionRepository.save(pollOption);
        }

        return new PollCreatedResponse(poll.getPollId());
    }

    @Transactional
    public void votePollOption(int pollId, int optionId, String userName) {
        User voter = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found", userName)));
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException(String.format("Poll with id %s not found", pollId)));
        PollOption pollOption = pollOptionRepository.findByPollAndOptionNum(poll, optionId).orElseThrow(() -> new PollOptionNotFoundException(String.format("Poll option with id %s not found", optionId)));
        Vote vote = voteRepository.findByUserIdAndPollId(voter.getUserId(), pollId).orElse(null);
        if (vote == null) {
            Vote newVote = Vote.builder().pollId(pollId).optionNum(optionId).userId(voter.getUserId()).build();
            voteRepository.save(newVote);
        } else if (vote.getOptionNum() != optionId) {
            vote.setOptionNum(optionId);
            voteRepository.save(vote);
        } else {
            voteRepository.delete(vote);
        }
    }

    public PollsResponse getPoll(int pollId) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException(String.format("Poll with id %s not found", pollId)));
        List<PollOption> pollOptions = pollOptionRepository.findByPoll(poll);
        PollsResponse pollsResponse = new PollsResponse();
        ArrayList<OptionDTO> options = new ArrayList<>();
        pollsResponse.setId(pollId);
        pollsResponse.setTitle(poll.getPollTopic());
        for (PollOption pollOption : pollOptions) {
            int numberOfVotes = voteRepository.countByPollIdAndOptionNum(pollId, pollOption.getOptionNum());
            var optionDTO = OptionDTO.builder().name(pollOption.getOptionText()).votes(numberOfVotes).build();
            options.add(optionDTO);
        }
        pollsResponse.setOptions(options);
        return pollsResponse;
    }

    public GetAllPollsResponse getPolls() {
        List<Poll> polls = pollRepository.findAll();
        GetAllPollsResponse responseDTO = new GetAllPollsResponse();
        ArrayList<PollDTO> pollDTOs = new ArrayList<>();
        for (Poll poll : polls) {
            PollDTO pollDTO = PollDTO.builder().id(poll.getPollId()).title(poll.getPollTopic()).build();
            pollDTOs.add(pollDTO);
        }
        responseDTO.setPolls(pollDTOs);
        return responseDTO;
    }

    @Transactional
    public void deletePoll(int pollId, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found", userName)));
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new PollNotFoundException(String.format("Poll with id %s not found", pollId)));
        if (!Objects.equals(poll.getUser().getUserId(), user.getUserId())) {
            throw new UserIsNotCreatorException("User is not the creator of the poll");
        }
        voteRepository.deleteByPollId(pollId);
        pollOptionRepository.deleteByPoll(poll);
        pollRepository.deleteById(pollId);
    }
}
