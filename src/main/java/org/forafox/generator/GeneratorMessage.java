package org.forafox.generator;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Topic;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.MessageDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GeneratorMessage {

    private final MessageServiceImpl messageService;

    public MessageDTO generateRandomMessageByLongNumber(Long topic_id) {
        var messageDto = new MessageDTO();
        messageDto.setAuthor("Author " + topic_id);
        messageDto.setText("Text " + topic_id);
        return messageDto;
    }

    @Transactional
    public void generateAndSaveRandomMessageByTopic(Topic topic) {
        for (long i = 1; i < 101; i++) {
            var messageDto = new MessageDTO();
            messageDto.setTopic(topic);
            messageDto.setAuthor("Author " + topic.getId() + " " + i);
            messageDto.setText("Text " + topic.getId() + " " + i);
            messageService.createMessage(messageDto);
        }
    }


}
