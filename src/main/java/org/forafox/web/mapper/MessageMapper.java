package org.forafox.web.mapper;

import org.forafox.domain.Message;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.mapper.abstract_mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Component
public class MessageMapper implements Mapper<Message, MessageDTO> {
    @Override
    public MessageDTO toDto(Message entity) {
        var messageDTO = new MessageDTO();
        messageDTO.setTopic(entity.getTopic());
        messageDTO.setText(entity.getText());
        messageDTO.setAuthor(entity.getAuthor());
        messageDTO.setCreatedAt(entity.getCreatedAt());
        messageDTO.setId(entity.getId());
        return messageDTO;
    }

    @Override
    public Message toEntity(MessageDTO dto, BiConsumer<Message, MessageDTO> block) {
        var message = new Message();
        message.setAuthor(dto.getAuthor());
        message.setText(dto.getText());
        message.setTopic(dto.getTopic());
        message.setCreatedAt(dto.getCreatedAt());
        return message;
    }

    @Override
    public List<MessageDTO> toDtos(List<Message> entities) {
        if (entities != null) {
            return entities.stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    @Override
    public List<Message> toEntities(List<MessageDTO> dtos) {
        if (dtos != null) {
            return dtos.stream()
                    .map(dto -> toEntity(dto, null))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

}
