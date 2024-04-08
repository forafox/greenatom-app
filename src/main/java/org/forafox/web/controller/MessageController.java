package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.responseRecord.MessageDeleteResponse;
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
    public MessageDeleteResponse deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessageById(messageId);
        return new MessageDeleteResponse("Succesfull", "Delete message");
    }
}
