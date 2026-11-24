package com.minikafka.broker.api.dto.response;

import java.time.Instant;

public record MessageResponse(long offset, String value, Instant timestamp) {}
