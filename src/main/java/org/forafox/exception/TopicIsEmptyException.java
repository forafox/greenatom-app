package org.forafox.exception;

public class TopicIsEmptyException  extends RuntimeException{
    public TopicIsEmptyException(String message) {
        super(message);
    }
}
