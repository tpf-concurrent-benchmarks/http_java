package com.tpf_benchmarks.http_server.repositories;

import com.tpf_benchmarks.http_server.entities.PollOption;
import com.tpf_benchmarks.http_server.entities.PollOptionId;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface PollOptionRepository extends JpaRepository<PollOption, PollOptionId>{
}
