package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.exception.AccessMessageDeniedException;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.exception.TopicIsEmptyException;
import org.forafox.repository.MessageRepository;
import org.forafox.service.MessageService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.MessageSliceDTO;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.security.principal.AuthenticationFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserServiceImpl userService;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public MessageDTO createMessage(MessageDTO message) {
        return createMessageEntity(message);
    }

    @Override
    public void deleteMessageById(Long messageId) {
        if (messageIsLastInTopic(getMessageById(messageId).getTopic().getId())) {
            throw new TopicIsEmptyException("An attempt to delete the last message in the topic!");
        }
        if (!getMessageById(messageId).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
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
        if (!getMessageById(messageDTO.getId()).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
            throw new AccessMessageDeniedException("You are not the owner of this message!");
        }
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
        message.setUser(userService.getByEmail(authenticationFacade.getAuthName()));
        return messageMapper.toDto(messageRepository.save(message));
    }

    private void setDateIfNullInMessage(MessageDTO message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(new Date());
        }
    }

    public MessageSliceDTO getMessageSliceDTOByTopicId(Long topicId, int pageOffset, int pageLimit) {
        Slice<Message> messageSlice = messageRepository.findAllSliceByTopicId(topicId, PageRequest.of(pageOffset, pageLimit));
        return new MessageSliceDTO(messageSlice.getContent(), messageSlice.getPageable());
    }

}