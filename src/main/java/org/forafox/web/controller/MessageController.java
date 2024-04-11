package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
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
@SecurityRequirement(name = "JWT")
@Tag(name = "Client API")
public class MessageController {
    private final MessageServiceImpl messageService;

    @DeleteMapping("/{messageId}")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Successful operation"),
            @ApiResponse(responseCode = "403", description = "You are not the owner of this message!", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Messages not found", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "An attempt to delete the last message in the topic!", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")})})
    @Operation(summary = "Delete message by ID",
            description = "Deletes an existing message by its ID",
            operationId = "deleteMessage")
    public ResponseEntity<String> deleteMessage(
            @PathVariable
            @Min(value = 0, message = "Message ID must be greater than or equal to 0")
            @Min(0) @Parameter(description = "ID of the message to delete", required = true) Long messageId) {
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }
}
