package com.tpf_benchmarks.http_server.exceptions;

public class UserIsNotCreatorException extends RuntimeException {
    public UserIsNotCreatorException(String message) {
        super(message);
    }
}
