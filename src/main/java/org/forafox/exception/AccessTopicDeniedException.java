package org.forafox.exception;

public class AccessTopicDeniedException extends RuntimeException{
    public AccessTopicDeniedException(String message) {
        super(message);
    }
}
