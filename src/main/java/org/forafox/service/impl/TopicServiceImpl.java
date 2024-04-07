package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Topic;
import org.forafox.domain.exception.ResourceNotFoundException;
import org.forafox.repository.TopicRepository;
import org.forafox.service.TopicService;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public TopicDTO createTopicEntity(TopicDTO topicDto) {
        if (topicRepository.findByTitle(topicDto.getTitle()).isPresent()) {
            throw new IllegalStateException("Topic already exists.");
        }
        var topic = topicMapper.toEntity(topicDto, null);
        topic.setTitle(topic.getTitle());
        topic = topicRepository.save(topic);
        messageService.createFirstTopicMessage(topic);
        return topicMapper.toDto(topic);
    }

    @Override
    public Topic getTopicByID(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public Topic updateTopicById(TopicDTO topicDTO) {
        var topic = getTopicByID(topicDTO.getId());
        topic.setTitle(topicDTO.getTitle());
        return topicRepository.save(topic);
    }

}