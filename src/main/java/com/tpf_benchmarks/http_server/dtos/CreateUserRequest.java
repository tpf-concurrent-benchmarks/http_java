package com.tpf_benchmarks.http_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty("username")
    @NotNull
    private String userName;
    @JsonProperty("password")
    @NotNull
    private String password;
}
