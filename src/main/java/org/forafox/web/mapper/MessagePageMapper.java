package org.forafox.web.mapper;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.MessagePageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessagePageMapper {
    private final MessageMapper messageMapper;

    public MessagePageDTO toDto(Page<Message> page) {
        var messagePageDTO = new MessagePageDTO();
        List<Message> list = page.getContent();
        List<MessageDTO> listDTO = messageMapper.toDtos(list);
        messagePageDTO.setMessagesList(listDTO);
        messagePageDTO.setEmpty(page.isEmpty());
        messagePageDTO.setLastPage(page.isLast());
        messagePageDTO.setFirstPage(page.isFirst());
        messagePageDTO.setPageSize(page.getSize());
        messagePageDTO.setPageNumber(page.getNumber());
        messagePageDTO.setTotalPages(page.getTotalPages());
        messagePageDTO.setTotalElements(page.getTotalElements());
        return messagePageDTO;
    }
}
