package com.minikafka.broker.log;

import java.time.Instant;

public record Message(long offset, byte[] value, Instant timestamp) {}