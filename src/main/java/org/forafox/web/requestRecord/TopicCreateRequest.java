package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreateRequest(@NotBlank String title, @NotNull MessageCreateRequest message) {

}