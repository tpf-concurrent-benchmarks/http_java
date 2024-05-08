# HTTP Server in Java

## Objective

This is a Java implementation of a poll HTTP server.

The objective of this project is to analyze the concurrent capabilities of Java, under various workloads, and to compare the performance between different languages.

## Deployment

### Requirements

- [Docker](https://www.docker.com/)
- [Java 17+](https://openjdk.org/) (optional, if you want to develop locally)

The project is containerized, so you don't need to have Java installed on your machine.

### Commands

#### Setup

- `make build`: Creates needed directories and builds the image with the binary
- `make create_directories`: Creates the needed directories

#### Run

- `make deploy`: Runs the binary and monitoring system in docker
- `make remove`: Stops and removes the containers and the image
- `make logs`: Shows the logs of the running container

## Libraries

- [Spring](https://spring.io/)