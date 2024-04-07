package org.forafox.web.responseRecord;

import jakarta.validation.constraints.NotNull;
import org.forafox.web.dto.MessageDTO;

import java.util.List;

public record TopicResponseWithMessages(@NotNull Long id, @NotNull String title, List<MessageDTO> messageDTOList) {
}