package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.exception.TopicIsEmptyException;
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
    public void deleteMessageById(Long messageId) {
        if (messageIsLastInTopic(getMessageById(messageId).getTopic().getId())) {
            throw new TopicIsEmptyException("An attempt to delete the last message in the topic!");
        }
        messageRepository.delete(getMessageById(messageId));
    }

    private boolean messageIsLastInTopic(Long topicId) {
        return getAllMessagesByTopicId(topicId).size()==1;
    }

    @Override
    public Message updateMessageById(MessageDTO messageDTO) {
        var message = getMessageById(messageDTO.getId());
        message.setTopic(messageDTO.getTopic());
        message.setText(messageDTO.getText());
        message.setAuthor(messageDTO.getAuthor());
        message.setCreatedAt(messageDTO.getCreatedAt());
        return messageRepository.save(message);
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }

    public List<Message> getAllMessagesByTopicId(Long topic_id) {
        return messageRepository.findByTopicId(topic_id).orElseThrow(() -> new ResourceNotFoundException("Messages not found"));
    }

    private MessageDTO createMessageEntity(MessageDTO messageDTO) {
        setDateIfNullInMessage(messageDTO);
        var message = messageMapper.toEntity(messageDTO, null);
        return messageMapper.toDto(messageRepository.save(message));
    }

    private void setDateIfNullInMessage(MessageDTO message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(new Date());
        }
    }


}