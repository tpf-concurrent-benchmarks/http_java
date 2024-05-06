package com.tpf_benchmarks.http_server.exceptions;

public class PollNotFoundException extends RuntimeException {
    public PollNotFoundException(String message) {
        super(message);
    }
}
