package org.forafox.web.responseRecord;

import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicInListDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    public TopicListResponse dtoListToResponse(List<TopicDTO> topicDTO) {
        List<TopicInListDTO> listAllTopics = topicDTO.stream()
                .map(dto -> new TopicInListDTO(dto.getId(), dto.getTitle()))
                .collect(Collectors.toList());

        return new TopicListResponse(listAllTopics);
    }

    public TopicResponse dtoToResponse(TopicDTO dto) {
        return new TopicResponse(dto.getId(), dto.getTitle());
    }

    public MessageResponse messageDtoToResponse(MessageDTO messageDTO) {
        return new MessageResponse(messageDTO.getId(), messageDTO.getAuthor(), messageDTO.getText(), messageDTO.getCreatedAt());
    }


}
