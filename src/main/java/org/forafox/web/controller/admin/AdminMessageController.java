package org.forafox.web.controller.admin;

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
@PreAuthorize("hasRole('ADMIN')")
public class AdminMessageController {

    private final MessageServiceImpl messageService;

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable @Min(0) Long messageId) {
        messageService.deleteMessageById(messageId);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }
}
