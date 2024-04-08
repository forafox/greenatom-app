package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateRequest(@NotNull Long id, String title) {

}