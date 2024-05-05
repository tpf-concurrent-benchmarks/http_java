package com.tpf_benchmarks.http_server.repositories;

import com.tpf_benchmarks.http_server.entities.Poll;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface PollRepository extends JpaRepository<Poll, Integer> {
}
