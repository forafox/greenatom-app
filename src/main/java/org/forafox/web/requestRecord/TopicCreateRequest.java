package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.forafox.web.dto.MessageDTO;

public record TopicCreateRequest(
        @NotBlank String title,
        @NotNull MessageDTO message) {
}