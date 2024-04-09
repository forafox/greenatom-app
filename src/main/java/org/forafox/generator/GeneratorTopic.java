package org.forafox.generator;

import lombok.RequiredArgsConstructor;
import org.forafox.service.impl.TopicServiceImpl;
import org.forafox.web.dto.TopicDTO;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GeneratorTopic {

    private final TopicServiceImpl topicService;
    private final GeneratorMessage generatorMessage;

    @Transactional
    @EventListener
    public void generateTopics(ContextRefreshedEvent event) {
        generateAndReturnIdTopicsWithFirstMessage();
        for(var topic: topicService.getAllTopics()){
            generatorMessage.generateAndSaveRandomMessageByTopic(topic);
        }
    }

    @Transactional
    public void generateAndReturnIdTopicsWithFirstMessage() {
        for (long i = 1; i < 101; i++) {
            var topicDto = new TopicDTO();
            topicDto.setTitle("Topic " + i);
            topicService.createTopicEntity(topicDto, generatorMessage.generateRandomMessageByLongNumber(i));
        }
    }

}