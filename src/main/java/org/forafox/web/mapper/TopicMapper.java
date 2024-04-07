package org.forafox.web.mapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.mapper.abstract_mapper.Mapper;
import org.jboss.logging.Messages;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TopicMapper implements Mapper<Topic, TopicDTO> {

    public TopicDTO toDtoWithMessages(Topic entity, List<MessageDTO> messages) {
        var topicDto = new TopicDTO();
        topicDto.setMessages(messages);
        topicDto.setTitle(entity.getTitle());
        topicDto.setId(entity.getId());
        return topicDto;
    }


    @Override
    public TopicDTO toDto(Topic entity) {
        var topicDto = new TopicDTO();
        topicDto.setTitle(entity.getTitle());
        topicDto.setId(entity.getId());
        return topicDto;
    }

    @Override
    public Topic toEntity(TopicDTO dto, BiConsumer<Topic, TopicDTO> block) {
        var topic = new Topic();
        topic.setTitle(dto.getTitle());
        return topic;
    }


    @Override
    public List<TopicDTO> toDtos(List<Topic> entities) {
        if (entities != null) {
            return entities.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    @Override
    public List<Topic> toEntities(List<TopicDTO> dtos) {
        if (dtos != null) {
            return dtos.stream()
                    .map(dto -> toEntity(dto, null))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }
}
