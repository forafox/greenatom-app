package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Role;
import org.forafox.domain.User;
import org.forafox.repository.MessageRepository;
import org.forafox.service.MessageService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.UserDto;
import org.forafox.web.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDTO createMessage(MessageDTO message) {
        if (messageRepository.findById(message.getId()).isPresent()) {
            throw new IllegalStateException("Message already exists.");
        }
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

    private MessageDTO createMessageEntity(MessageDTO messageDTO) {
        var message = messageMapper.toEntity(messageDTO, null);
        return messageMapper.toDto(messageRepository.save(message));
    }

}