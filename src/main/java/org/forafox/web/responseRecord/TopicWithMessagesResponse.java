package org.forafox.web.responseRecord;

import java.util.List;

public record TopicWithMessagesResponse(Long id, String title, List<MessageResponse> messageDTOList) {
}