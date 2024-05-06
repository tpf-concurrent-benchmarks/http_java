package com.tpf_benchmarks.http_server.repositories;

import com.tpf_benchmarks.http_server.entities.Vote;
import com.tpf_benchmarks.http_server.entities.VoteId;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    Optional<Vote> findByUserIdAndPollId(Integer userId, int pollId);
}
