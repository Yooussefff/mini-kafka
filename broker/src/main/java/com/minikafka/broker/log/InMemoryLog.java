package com.minikafka.broker.log;

import com.minikafka.broker.exception.OffsetOutOfRangeException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InMemoryLog implements Log {
    private final List<Message> messages = new ArrayList<>();
    private final Object lock = new Object();

    @Override
    public List<Message> read(long offset, int maxMessages) {
        synchronized (lock) {

            if(maxMessages <= 0){
                throw new IllegalArgumentException("maxMessages must be greater than 0");
            }

            int size = messages.size();

            if (offset < 0 || offset > size) {
                throw new OffsetOutOfRangeException(offset, size);
            }

            int from = (int) offset;
            int to = (int) Math.min((long) from + maxMessages, size);

            return List.copyOf(messages.subList(from, to));
        }
    }

    @Override
    public long append(byte[] value) {
        synchronized (lock) {
            Message message = new Message(messages.size(), value, Instant.now());
            messages.add(message);
            return message.offset();
        }
    }
}