package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateRequest(@NotNull Long id, @NotBlank String title) {

}