package org.forafox.web.requestRecord;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TopicCreateRequest(@NotEmpty String title, MessageCreateRequest message) {

}