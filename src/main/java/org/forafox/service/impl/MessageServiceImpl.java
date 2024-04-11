package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.exception.AccessMessageDeniedException;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.exception.TopicIsEmptyException;
import org.forafox.repository.MessageRepository;
import org.forafox.service.MessageService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.MessagePageDTO;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.MessagePageMapper;
import org.forafox.web.security.principal.AuthenticationFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserServiceImpl userService;
    private final MessagePageMapper messagePageMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public MessageDTO createMessage(MessageDTO message, Topic topic) {
        return createMessageEntity(message, topic);
    }

    @Override
    public void deleteMessageById(Long messageId) {
        if (messageIsLastInTopic(getMessageById(messageId).getTopic().getId())) {
            throw new TopicIsEmptyException("An attempt to delete the last message in the topic!");
        }
        if (!authenticationFacade.isAdmin() && !getMessageById(messageId).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
            throw new AccessMessageDeniedException("You are not the owner of this message!");
        }
        if (messageRepository.findById(messageId).isEmpty()) {
            throw new ResourceNotFoundException("Messages not found");
        }
        messageRepository.delete(getMessageById(messageId));
    }


    private boolean messageIsLastInTopic(Long topicId) {
        return getAllMessagesByTopicId(topicId).size() == 1;
    }

    @Override
    public Message updateMessageById(MessageDTO messageDTO) {
        if (!authenticationFacade.isAdmin() && !getMessageById(messageDTO.getId()).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
            throw new AccessMessageDeniedException("You are not the owner of this message!");
        }
        var message = getMessageById(messageDTO.getId());
        message.setText(messageDTO.getText());
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

    private MessageDTO createMessageEntity(MessageDTO messageDTO, Topic topic) {
        setDateIfNullInMessage(messageDTO);
        setAuthorIfNullInMessage(messageDTO);
        var message = messageMapper.toEntity(messageDTO, null);
        message.setUser(userService.getByEmail(authenticationFacade.getAuthName()));
        message.setTopic(topic);
        return messageMapper.toDto(messageRepository.save(message));
    }

    private void setDateIfNullInMessage(MessageDTO message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(new Date());
        }
    }

    private void setAuthorIfNullInMessage(MessageDTO message) {
        if (message.getAuthor() == null) {
            message.setAuthor(authenticationFacade.getAuthName());
        }
    }

    public MessagePageDTO getMessagePageDTOByTopicId(Long topicId, int pageOffset, int pageLimit) {
        Page<Message> messagePage = messageRepository.findAllPageByTopicId(topicId, PageRequest.of(pageOffset, pageLimit));
        return messagePageMapper.toDto(messagePage);
    }

}