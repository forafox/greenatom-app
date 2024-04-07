package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotNull;

public record TopicCreateRequest(@NotNull String title) {

}