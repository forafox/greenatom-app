package org.forafox.exception;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private final String VALIDATION_EXCEPTION = "Validation exception";
    private final String INVALID_INPUT_EXCEPTION = "Invalid input";
    private final String NOT_FOUND_EXCEPTION = "Not found";
    private final String CONFLICT_EXCEPTION = "Conflict with the current state of the target resource.";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), NOT_FOUND_EXCEPTION, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), new Date(), VALIDATION_EXCEPTION, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TopicIsEmptyException.class)
    public ResponseEntity<ErrorMessage> topicIsEmptyException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), new Date(), CONFLICT_EXCEPTION, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {JsonParseException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> jsonParseException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), INVALID_INPUT_EXCEPTION, ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), request.getDescription(false), ex.getMessage());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}