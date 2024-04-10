package org.forafox.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicPageDTO {
    List<TopicDTO> topicsList;
    boolean isFirstPage;
    boolean isLastPage;
    boolean isEmpty;
    int totalPages;
    long totalElements;
    int pageSize;
    int pageNumber;
}