package com.minikafka.broker.api;

import com.minikafka.broker.api.dto.request.CreateTopicRequest;
import com.minikafka.broker.topic.TopicManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class TopicController {

    private final TopicManager topicManager;

    public TopicController(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    @PostMapping("/topics")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTopic(@RequestBody CreateTopicRequest request) {
        topicManager.createTopic(request.name());
    }

    @GetMapping("/topics")
    public Set<String> listTopics() {
        return topicManager.listTopics();
    }
}