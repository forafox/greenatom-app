package org.forafox.web.responseRecord;

import org.forafox.web.dto.TopicInListDTO;

import java.util.List;

public record TopicListResponse(List<TopicInListDTO> listAllTopics) {
}
