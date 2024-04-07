package org.forafox.service;

import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicInListDTO;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics();

    TopicDTO createTopicEntity(TopicDTO topic);

    Topic getTopicByID(Long id);

}