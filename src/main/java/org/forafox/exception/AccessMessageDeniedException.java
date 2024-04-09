package org.forafox.exception;

public class AccessMessageDeniedException extends RuntimeException {
    public AccessMessageDeniedException(String message) {
        super(message);
    }
}
