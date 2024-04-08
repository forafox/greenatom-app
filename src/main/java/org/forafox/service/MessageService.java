package org.forafox.service;

import org.forafox.domain.Message;
import org.forafox.web.dto.MessageDTO;

public interface MessageService {
    MessageDTO createMessage(MessageDTO message);

    Message updateMessageById(MessageDTO messageDTO);

    Message getMessageById(Long id);

    void deleteMessageById(Long messageId);
}