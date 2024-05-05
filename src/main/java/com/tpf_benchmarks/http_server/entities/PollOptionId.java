package com.tpf_benchmarks.http_server.entities;

import java.io.Serializable;
import java.util.Objects;

public class PollOptionId implements Serializable {

    private Poll poll;
    private Integer optionNum;

    public PollOptionId() {}

    public PollOptionId(Poll poll, Integer optionNum) {
        this.poll = poll;
        this.optionNum = optionNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollOptionId that = (PollOptionId) o;
        return Objects.equals(poll, that.poll) &&
                Objects.equals(optionNum, that.optionNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poll, optionNum);
    }
}
