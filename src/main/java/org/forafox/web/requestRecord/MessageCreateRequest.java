package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotBlank;

public record MessageCreateRequest(@NotBlank String text, @NotBlank String author) {
}
