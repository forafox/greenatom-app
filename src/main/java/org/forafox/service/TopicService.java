package org.forafox.service;

import org.forafox.domain.Topic;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicPageDTO;

import java.util.List;

public interface TopicService {
    TopicPageDTO getAllPageTopicsDTO(int offset, int limit);

    List<Topic> getAllTopics();

    TopicDTO createTopicEntity(TopicDTO topic, MessageDTO messageDTO);

    Topic getTopicByID(Long id);

    Topic updateTopicById(TopicDTO topicDTO);

    void deleteTopicById(Long topicId);
}