package com.minikafka.broker.topic;

import com.minikafka.broker.exception.TopicAlreadyExistsException;
import com.minikafka.broker.exception.TopicNotFoundException;
import com.minikafka.broker.log.InMemoryLog;
import com.minikafka.broker.log.Log;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TopicManager {
    private final Map<String, Log>topics = new HashMap<>();

    private final Object lock = new Object();

    public void createTopic(String topic) {
        synchronized (lock) {

            if (topics.containsKey(topic)) {
                throw new TopicAlreadyExistsException(topic);
            }

            topics.put(topic, new InMemoryLog());
        }
    }

    public Log getLog(String topic) {
        synchronized (lock) {

            if (!topics.containsKey(topic)) {
                throw new TopicNotFoundException(topic);
            }

            return topics.get(topic);
        }
    }

    public Set<String> listTopics() {
        synchronized (lock) {
            return Set.copyOf(topics.keySet());
        }
    }
}
