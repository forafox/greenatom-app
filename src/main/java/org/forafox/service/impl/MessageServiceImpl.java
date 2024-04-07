package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.domain.exception.ResourceNotFoundException;
import org.forafox.repository.MessageRepository;
import org.forafox.service.MessageService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDTO createMessage(MessageDTO message) {
        return createMessageEntity(message);
    }

    @Override
    public MessageDTO editMessage(MessageDTO message, String newText) {
//        message.setText(newText);
//        return messageRepository.save(message);
        return null;
    }

    @Override
    public void deleteMessage(MessageDTO message) {
//        messageRepository.delete(message);
    }

    public List<Message> getAllMessagesByTopicId(Long topic_id) {
        return messageRepository.findByTopicId(topic_id).orElseThrow(() -> new ResourceNotFoundException("Messages not found"));
    }

    private MessageDTO createMessageEntity(MessageDTO messageDTO) {
        setDateIfNullInMessage(messageDTO);
        var message = messageMapper.toEntity(messageDTO, null);
        return messageMapper.toDto(messageRepository.save(message));
    }

    public void createFirstTopicMessage(Topic topic) {
        var message = new MessageDTO(null, topic, "Admin", "First topic message!", null);
        createMessageEntity(message);
    }

    private void setDateIfNullInMessage(MessageDTO message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(new Date());
        }
    }


}