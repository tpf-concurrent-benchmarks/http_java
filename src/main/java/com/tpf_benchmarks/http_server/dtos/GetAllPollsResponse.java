package com.tpf_benchmarks.http_server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPollsResponse {

        public ArrayList<PollDTO> polls;
}
