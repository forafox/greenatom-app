package org.forafox.web.responseRecord;

import jakarta.validation.constraints.NotNull;

public record TopicResponse(@NotNull Long id, @NotNull String title) {
}
