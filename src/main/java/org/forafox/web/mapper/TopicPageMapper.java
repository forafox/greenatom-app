package org.forafox.web.mapper;

import lombok.RequiredArgsConstructor;
import org.forafox.domain.Topic;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TopicPageMapper {
    private final TopicMapper topicMapper;

    public TopicPageDTO toDto(Page<Topic> page) {
        var topicPageDTO = new TopicPageDTO();
        List<Topic> list = page.getContent();
        List<TopicDTO> listDTO = topicMapper.toDtos(list);
        topicPageDTO.setTopicsList(listDTO);
        topicPageDTO.setEmpty(page.isEmpty());
        topicPageDTO.setLastPage(page.isLast());
        topicPageDTO.setFirstPage(page.isFirst());
        topicPageDTO.setPageSize(page.getSize());
        topicPageDTO.setPageNumber(page.getNumber());
        topicPageDTO.setTotalPages(page.getTotalPages());
        topicPageDTO.setTotalElements(page.getTotalElements());
        return topicPageDTO;
    }
}
