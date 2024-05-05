package com.tpf_benchmarks.http_server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String title;
    @JsonProperty("options")
    private String[] options;

}
