package org.forafox.service;

import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.web.dto.MessageDTO;

public interface MessageService {
    MessageDTO createMessage(MessageDTO message, Topic topic);

    Message updateMessageById(MessageDTO messageDTO);

    Message getMessageById(Long id);

    void deleteMessageById(Long messageId);
}