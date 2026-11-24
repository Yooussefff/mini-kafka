package com.minikafka.broker.api;

import com.minikafka.broker.api.dto.request.ProduceRequest;
import com.minikafka.broker.api.dto.response.MessageResponse;
import com.minikafka.broker.api.dto.response.ProduceResponse;
import com.minikafka.broker.log.Log;
import com.minikafka.broker.log.Message;
import com.minikafka.broker.topic.TopicManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/topics/{topic}/messages")
public class MessageController {

    private final TopicManager topicManager;

    public MessageController(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    @PostMapping
    public ProduceResponse produce(@PathVariable String topic,
                                   @RequestBody ProduceRequest request) {
        Log log = topicManager.getLog(topic);
        byte[] value = request.value().getBytes(StandardCharsets.UTF_8);
        long offset = log.append(value);
        return new ProduceResponse(offset);
    }

    @GetMapping
    public List<MessageResponse> consume(@PathVariable String topic,
                                         @RequestParam long offset,
                                         @RequestParam(defaultValue = "100") int max) {
        Log log = topicManager.getLog(topic);
        List<Message> messages = log.read(offset, max);
        return messages.stream()
                .map(m -> new MessageResponse(
                        m.offset(),
                        new String(m.value(), StandardCharsets.UTF_8),
                        m.timestamp()))
                .toList();
    }
}