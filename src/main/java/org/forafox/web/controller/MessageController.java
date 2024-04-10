package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.forafox.service.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "JWT")
@Tag(name = "Messages")
public class MessageController {
    private final MessageServiceImpl messageService;

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Delete message by ID",
            description = "Deletes an existing message by its ID",
            operationId = "deleteMessage")
    public ResponseEntity<String> deleteMessage(@PathVariable @Min(0) Long messageId) {
        messageService.deleteMessageById(messageId);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }
}
