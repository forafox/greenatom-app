package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotNull;

public record TopicWithDataRequest(@NotNull Long id, String title) {

}