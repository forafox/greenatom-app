package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MessageUpdateRequest(@NotNull Long id, @NotBlank String text, @NotBlank String author,
                                   @NotNull Date created) {
}
