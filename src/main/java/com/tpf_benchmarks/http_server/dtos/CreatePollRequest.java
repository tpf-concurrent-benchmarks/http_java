package com.tpf_benchmarks.http_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePollRequest {

    @JsonProperty("title")
    @NotNull
    @Size(min = 1)
    private String title;
    @JsonProperty("options")
    @NotNull
    @Size(min = 2, max = 10)
    private String[] options;

}
