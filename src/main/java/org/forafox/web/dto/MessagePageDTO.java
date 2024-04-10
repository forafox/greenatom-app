package org.forafox.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.forafox.domain.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePageDTO {
    List<MessageDTO> messagesList;
    boolean isFirstPage;
    boolean isLastPage;
    boolean isEmpty;
    int totalPages;
    long totalElements;
    int pageSize;
    int pageNumber;
}
