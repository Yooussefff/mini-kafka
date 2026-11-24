package com.minikafka.broker.exception;

public class TopicAlreadyExistsException extends RuntimeException {
    public TopicAlreadyExistsException(String topic) {
        super("Topic " + topic + " already exists");
    }
}
