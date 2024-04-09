package org.forafox.service.impl;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.exception.ResourceNotFoundException;
import org.forafox.repository.TopicRepository;
import org.forafox.service.TopicService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.MessageSliceDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicSliceDTO;
import org.forafox.web.mapper.TopicMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;

    @Override
    public TopicSliceDTO getAllSliceTopicsDTO(int offset, int limit) {
        Slice<Topic>  topicsSlice =  topicRepository.findAllSlice(PageRequest.of(offset, limit));
        return new TopicSliceDTO(topicsSlice.getContent(),topicsSlice.getPageable());

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
        topic = topicRepository.save(topic);
        message.setTopic(topic);
        messageService.createMessage(message);
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


    @Override
    public void deleteTopicById(Long topicId) {
        topicRepository.delete(getTopicByID(topicId));
    }
}