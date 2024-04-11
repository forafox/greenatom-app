package org.forafox.web.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.forafox.service.impl.MessageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/message")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAuthority('ADMIN')")
@SecurityRequirement(name = "JWT")
@Tag(name ="Admin API")
public class AdminMessageController {

    private final MessageServiceImpl messageService;

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Delete message by ID",
            description = "Deletes an existing message by its ID",
            operationId = "deleteMessage",tags = "Admin API")
    public ResponseEntity<String> deleteMessage(
            @PathVariable
            @Min(value = 0, message = "Message ID must be greater than or equal to 0")
            @Parameter(description = "ID of the message to delete", required = true) Long messageId) {
        messageService.deleteMessageById(messageId);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }
}
