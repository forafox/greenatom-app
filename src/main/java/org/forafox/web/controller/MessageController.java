package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.exception.ErrorMessage;
import org.forafox.service.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Validated
@Tag(name = "Message controller", description = "Topic API")
public class MessageController {
    private final MessageServiceImpl messageService;

    @DeleteMapping("/{messageId}")
    @Operation(description = "Delete an existing message by Id", operationId = "deleteMessage", tags = "Client API")
    public ResponseEntity<String> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessageById(messageId);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }
}
