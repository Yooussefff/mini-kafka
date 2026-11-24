package com.minikafka.broker.log;

import java.io.IOException;
import java.util.List;

public interface Log {
    public List<Message> read(long offset,  int maxMessages);
    public long append(byte[] message);
}
