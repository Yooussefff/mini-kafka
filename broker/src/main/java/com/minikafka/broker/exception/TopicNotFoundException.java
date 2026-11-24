package com.minikafka.broker.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String topic) {
    }
}
