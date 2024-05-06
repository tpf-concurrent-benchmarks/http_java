package com.tpf_benchmarks.http_server.exceptions;

public class PollOptionNotFoundException extends RuntimeException {
    public PollOptionNotFoundException(String message) {
        super(message);
    }
}
