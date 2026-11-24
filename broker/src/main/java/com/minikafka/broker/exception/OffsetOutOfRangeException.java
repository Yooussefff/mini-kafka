package com.minikafka.broker.exception;

public class OffsetOutOfRangeException extends RuntimeException {

    public OffsetOutOfRangeException(long offset, int size) {
        super("Offset " + offset + " is out of range [0, " + size + "]");
    }
}
