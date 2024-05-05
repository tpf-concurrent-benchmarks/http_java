package com.tpf_benchmarks.http_server.entities;

import java.io.Serializable;
import java.util.Objects;

public class VoteId implements Serializable {

    private Integer userId;
    private Integer pollId;

    public VoteId() {}

    public VoteId(Integer userId, Integer pollId) {
        this.userId = userId;
        this.pollId = pollId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteId voteId = (VoteId) o;
        return Objects.equals(userId, voteId.userId) &&
                Objects.equals(pollId, voteId.pollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, pollId);
    }
}
