package com.tpf_benchmarks.http_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHttpServerApplication {

	public static void main(String[] args) {
		SpringApplication.from(HttpServerApplication::main).with(TestHttpServerApplication.class).run(args);
	}

}
