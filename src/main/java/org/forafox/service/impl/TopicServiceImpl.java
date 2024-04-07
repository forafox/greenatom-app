package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Role;
import org.forafox.domain.Topic;
import org.forafox.domain.User;
import org.forafox.repository.TopicRepository;
import org.forafox.service.MessageService;
import org.forafox.service.TopicService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.UserDto;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;
    private final MessageMapper messageMapper;

    private final MessageService messageService;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public TopicDTO createTopicEntity(TopicDTO topicDto) {
        if (topicRepository.findByTitle(topicDto.getTitle()).isPresent()){
            throw new IllegalStateException("Topic already exists.");
        }
        var topic = topicMapper.toEntity(topicDto, null);
        topic.setTitle(topic.getTitle());
        return topicMapper.toDto(topicRepository.save(topic));
    }

}