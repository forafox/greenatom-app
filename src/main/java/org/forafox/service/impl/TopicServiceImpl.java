package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Topic;
import org.forafox.exception.AccessTopicDeniedException;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.repository.TopicRepository;
import org.forafox.service.TopicService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicPageDTO;
import org.forafox.web.mapper.TopicMapper;
import org.forafox.web.mapper.TopicPageMapper;
import org.forafox.web.security.principal.AuthenticationFacade;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {
    private final UserServiceImpl userService;
    private final TopicRepository topicRepository;
    private final AuthenticationFacade authenticationFacade;
    private final TopicPageMapper topicPageMapper;
    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;

    @Override
    public TopicPageDTO getAllPageTopicsDTO(int offset, int limit) {
        Page<Topic> topicsPage = topicRepository.findAllPage(PageRequest.of(offset, limit));
        return topicPageMapper.toDto(topicsPage);

    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public TopicDTO createTopicEntity(TopicDTO topicDto, MessageDTO message) {
        if (topicRepository.findByTitle(topicDto.getTitle()).isPresent()) {
            throw new IllegalStateException("Topic already exists.");
        }
        var topic = topicMapper.toEntity(topicDto, null);
        topic.setTitle(topic.getTitle());
        topic.setUser(userService.getByEmail(authenticationFacade.getAuthName()));
        topic = topicRepository.save(topic);
        messageService.createMessage(message,topic);
        return topicMapper.toDto(topic);
    }

    @Override
    public Topic getTopicByID(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public Topic updateTopicById(TopicDTO topicDTO) {
        if (!getTopicByID(topicDTO.getId()).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
            throw new AccessTopicDeniedException("You are not the owner of this topic!");
        }
        var topic = getTopicByID(topicDTO.getId());
        topic.setTitle(topicDTO.getTitle());
        return topicRepository.save(topic);
    }


    @Override
    public void deleteTopicById(Long topicId) {
        if (!getTopicByID(topicId).getUser().getUsername().equals(authenticationFacade.getAuthName())) {
            throw new AccessTopicDeniedException("You are not the owner of this topic!");
        }
        topicRepository.delete(getTopicByID(topicId));
    }

}