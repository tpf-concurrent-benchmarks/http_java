package com.tpf_benchmarks.http_server.repositories;

import com.tpf_benchmarks.http_server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
