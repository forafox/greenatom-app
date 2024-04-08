package org.forafox.service;

import org.forafox.domain.Message;
import org.forafox.web.dto.MessageDTO;

public interface MessageService {
    MessageDTO createMessage(MessageDTO message);

    MessageDTO editMessage(MessageDTO message, String newText);

    void deleteMessage(MessageDTO message);

    Message updateMessageById(MessageDTO messageDTO);

    Message getMessageById(Long id);

    void deleteMessageById(Long messageId);
}